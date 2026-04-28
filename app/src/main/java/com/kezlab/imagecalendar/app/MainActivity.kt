package com.kezlab.imagecalendar.app

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kezlab.imagecalendar.BuildConfig
import com.kezlab.imagecalendar.core.designsystem.AppColors
import com.kezlab.imagecalendar.core.designsystem.ImageCalendarTheme
import com.kezlab.imagecalendar.core.model.EmotionTag
import com.kezlab.imagecalendar.core.model.PhotoEntry
import com.kezlab.imagecalendar.core.repository.AppContainer
import com.kezlab.imagecalendar.core.repository.CreatePhotoEntryInput
import com.kezlab.imagecalendar.core.repository.PhotoEntryRepository
import java.io.File
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageCalendarTheme {
                ImageCalendarApp()
            }
        }
    }
}

private enum class AppTab(val label: String, val iconLabel: String, val a11yLabel: String) {
    Calendar("Calendar", "Cal", "Calendar tab"),
    Add("Add", "+", "Add record tab"),
    Archive("Archive", "Arc", "Archive tab"),
    Settings("Settings", "Set", "Settings tab"),
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ImageCalendarApp() {
    val context = LocalContext.current
    val repository = remember(context) { AppContainer.photoEntryRepository(context) }
    val entries by repository.observeEntries().collectAsState(initial = emptyList())
    var currentTab by remember { mutableStateOf(AppTab.Calendar) }
    var selectedDate by remember { mutableStateOf(LocalDate.now().toIsoDate()) }
    var dayDetailDate by remember { mutableStateOf<String?>(null) }

    Scaffold(
        containerColor = AppColors.Bg,
        bottomBar = {
            NavigationBar(containerColor = AppColors.Card) {
                AppTab.entries.forEach { tab ->
                    NavigationBarItem(
                        modifier = Modifier
                            .testTag("tab_${tab.label.lowercase()}")
                            .semantics { contentDescription = tab.a11yLabel },
                        selected = currentTab == tab && dayDetailDate == null,
                        onClick = {
                            dayDetailDate = null
                            currentTab = tab
                        },
                        label = { Text(tab.label) },
                        icon = { Text(tab.iconLabel) },
                    )
                }
            }
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(AppColors.Bg)
                .semantics { testTagsAsResourceId = true },
        ) {
            val detailDate = dayDetailDate
            if (detailDate != null) {
                DayDetailScreen(
                    localDate = detailDate,
                    entries = entries.filter { it.localDate == detailDate },
                    onBack = {
                        dayDetailDate = null
                        currentTab = AppTab.Calendar
                    },
                )
            } else when (currentTab) {
                AppTab.Calendar -> CalendarScreen(
                    entries = entries,
                    onAdd = {
                        selectedDate = LocalDate.now().toIsoDate()
                        currentTab = AppTab.Add
                    },
                    onDateSelected = { date ->
                        selectedDate = date
                        currentTab = AppTab.Calendar
                    },
                    onOpenDay = { date ->
                        selectedDate = date
                        dayDetailDate = date
                    },
                )

                AppTab.Add -> AddRecordScreen(
                    defaultDate = selectedDate,
                    onSaved = { entry ->
                        selectedDate = entry.localDate
                        dayDetailDate = entry.localDate
                    },
                    repository = repository,
                )

                AppTab.Archive -> ArchiveScreen(entries)
                AppTab.Settings -> SettingsScreen()
            }
        }
    }
}

@Composable
private fun CalendarScreen(
    entries: List<PhotoEntry>,
    onAdd: () -> Unit,
    onDateSelected: (String) -> Unit,
    onOpenDay: (String) -> Unit,
) {
    var month by remember { mutableStateOf(YearMonth.now()) }
    val today = LocalDate.now()
    val entryCounts = entries.groupingBy { it.localDate }.eachCount()
    val selectedDate = remember(entries) { entries.lastOrNull()?.localDate }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Image Calendar", style = MaterialTheme.typography.headlineMedium, color = AppColors.Text)
                LocalBadge()
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TextButton(
                    onClick = { month = month.minusMonths(1) },
                    modifier = Modifier
                        .testTag("calendar_previous_month")
                        .semantics { contentDescription = "Previous month" },
                ) { Text("이전") }
                Text("${month.year}.${month.monthValue.toString().padStart(2, '0')}", fontWeight = FontWeight.Bold)
                TextButton(
                    onClick = { month = month.plusMonths(1) },
                    modifier = Modifier
                        .testTag("calendar_next_month")
                        .semantics { contentDescription = "Next month" },
                ) { Text("다음") }
            }
        }
        item {
            MonthGrid(
                month = month,
                today = today,
                entryCounts = entryCounts,
                onDateSelected = onDateSelected,
                onOpenDay = onOpenDay,
            )
        }
        item {
            Button(
                onClick = onAdd,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("calendar_add_today")
                    .semantics { contentDescription = "Add today's record" },
                colors = ButtonDefaults.buttonColors(containerColor = AppColors.Accent),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text("오늘 기록 추가하기")
            }
        }
        item {
            if (selectedDate == null) {
                EmptyCard("아직 기록이 없어요", "사진 한 장으로 오늘을 캘린더에 남겨보세요.")
            } else {
                DayDetailCard(selectedDate, entries.filter { it.localDate == selectedDate })
            }
        }
    }
}

@Composable
private fun MonthGrid(
    month: YearMonth,
    today: LocalDate,
    entryCounts: Map<String, Int>,
    onDateSelected: (String) -> Unit,
    onOpenDay: (String) -> Unit,
) {
    val days = remember(month) {
        val first = month.atDay(1)
        val leading = first.dayOfWeek.value % 7
        List(leading) { null } + (1..month.lengthOfMonth()).map { month.atDay(it) }
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        listOf("일", "월", "화", "수", "목", "금", "토").chunked(7).forEach { labels ->
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.fillMaxWidth()) {
                labels.forEach {
                    Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        Text(it, color = AppColors.Text2, style = MaterialTheme.typography.labelMedium)
                    }
                }
            }
        }
        days.chunked(7).forEach { week ->
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.fillMaxWidth()) {
                week.forEach { date ->
                    DateCell(
                        date = date,
                        isToday = date == today,
                        count = entryCounts[date?.toIsoDate()] ?: 0,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            date?.let {
                                val iso = it.toIsoDate()
                                onDateSelected(iso)
                                if ((entryCounts[iso] ?: 0) > 0) onOpenDay(iso)
                            }
                        },
                    )
                }
                repeat(7 - week.size) {
                    Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun DateCell(
    date: LocalDate?,
    isToday: Boolean,
    count: Int,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(14.dp))
            .background(if (isToday) AppColors.AccentLight else AppColors.Card)
            .border(1.dp, if (isToday) AppColors.Accent else AppColors.Border, RoundedCornerShape(14.dp))
            .then(
                if (date != null) {
                    Modifier
                        .testTag("calendar_day_${date.toIsoDate()}")
                        .semantics {
                            contentDescription = buildDateCellDescription(date, isToday, count)
                        }
                } else {
                    Modifier
                },
            )
            .clickable(enabled = date != null, onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        if (date != null) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(date.dayOfMonth.toString(), color = AppColors.Text)
                if (count > 0) {
                    Spacer(Modifier.height(5.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        repeat(count.coerceAtMost(3)) {
                            Box(
                                Modifier
                                    .size(5.dp)
                                    .background(AppColors.Accent, CircleShape),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AddRecordScreen(
    defaultDate: String,
    repository: PhotoEntryRepository,
    onSaved: (PhotoEntry) -> Unit,
) {
    val scope = rememberCoroutineScope()
    var selectedUri by remember { mutableStateOf<Uri?>(null) }
    var debugFixtureSelected by remember { mutableStateOf(false) }
    var note by remember { mutableStateOf("") }
    var emotion by remember { mutableStateOf<EmotionTag?>(null) }
    var localDate by remember { mutableStateOf(defaultDate.ifBlank { LocalDate.now().toIsoDate() }) }
    var saving by remember { mutableStateOf(false) }
    var photoError by remember { mutableStateOf<String?>(null) }
    var saveError by remember { mutableStateOf<String?>(null) }
    val picker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedUri = uri
            debugFixtureSelected = false
            if (uri != null) photoError = null
        },
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Text("오늘의 기록", style = MaterialTheme.typography.headlineMedium, color = AppColors.Text)
            Spacer(Modifier.height(8.dp))
            LocalBadge()
        }
        item {
            CardBlock {
                Text("사진 (필수)", fontWeight = FontWeight.Bold, color = AppColors.Text)
                Spacer(Modifier.height(10.dp))
                Button(
                    onClick = {
                        picker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("add_photo_picker")
                        .semantics { contentDescription = "Select photo from gallery" },
                    colors = ButtonDefaults.buttonColors(containerColor = AppColors.Accent),
                ) {
                    Text(if (selectedUri == null && !debugFixtureSelected) "갤러리에서 사진 선택" else "다른 사진 선택")
                }
                if (BuildConfig.DEBUG) {
                    Spacer(Modifier.height(8.dp))
                    TextButton(
                        onClick = {
                            selectedUri = null
                            debugFixtureSelected = true
                            photoError = null
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("add_debug_fixture_photo")
                            .semantics { contentDescription = "Use QA test photo" },
                    ) {
                        Text("QA 테스트 사진 사용")
                    }
                }
                photoError?.let {
                    Spacer(Modifier.height(8.dp))
                    Text(it, color = AppColors.Danger)
                }
                if (selectedUri != null || debugFixtureSelected) {
                    Spacer(Modifier.height(10.dp))
                    Text("사진이 선택됐어요. 저장하면 앱 안에 복사본을 보관해요.", color = AppColors.Text2)
                }
            }
        }
        item {
            CardBlock {
                Text("날짜", fontWeight = FontWeight.Bold, color = AppColors.Text)
                TextField(
                    value = localDate,
                    onValueChange = { localDate = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("add_local_date")
                        .semantics { contentDescription = "Record date" },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = AppColors.Bg2,
                        unfocusedContainerColor = AppColors.Bg2,
                    ),
                )
            }
        }
        item {
            CardBlock {
                Text("메모 (선택)", fontWeight = FontWeight.Bold, color = AppColors.Text)
                TextField(
                    value = note,
                    onValueChange = { note = it },
                    placeholder = { Text("짧게 남겨보세요") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("add_note")
                        .semantics { contentDescription = "Optional note" },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = AppColors.Bg2,
                        unfocusedContainerColor = AppColors.Bg2,
                    ),
                )
            }
        }
        item {
            CardBlock {
                Text("감정 태그 (선택)", fontWeight = FontWeight.Bold, color = AppColors.Text)
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    EmotionTag.entries.forEach { tag ->
                        FilterChip(
                            modifier = Modifier
                                .testTag("add_emotion_${tag.name.lowercase()}")
                                .semantics { contentDescription = "Emotion ${tag.label}" },
                            selected = emotion == tag,
                            onClick = { emotion = if (emotion == tag) null else tag },
                            label = { Text(tag.label) },
                        )
                    }
                }
            }
        }
        item {
            saveError?.let { Text(it, color = AppColors.Danger) }
            Button(
                enabled = !saving,
                onClick = {
                    val uri = selectedUri
                    if (uri == null && !debugFixtureSelected) {
                        photoError = "사진을 먼저 선택해주세요."
                        saveError = null
                        return@Button
                    }
                    val normalizedDate = localDate.toValidIsoDateOrNull()
                    if (normalizedDate == null) {
                        saveError = "날짜는 YYYY-MM-DD 형식으로 입력해주세요."
                        return@Button
                    }
                    saving = true
                    photoError = null
                    saveError = null
                    scope.launch {
                        runCatching {
                            val input = CreatePhotoEntryInput(
                                localDate = normalizedDate,
                                note = note,
                                emotionTag = emotion,
                            )
                            withContext(Dispatchers.IO) {
                                if (debugFixtureSelected) {
                                    repository.createDebugFixtureEntry(input)
                                } else {
                                    requireNotNull(uri)
                                    repository.createPhotoEntryFromUri(uri, input)
                                }
                            }
                        }.onSuccess { entry ->
                            onSaved(entry)
                        }.onFailure {
                            saveError = "사진을 앱 안에 저장하지 못했어요. 다시 시도해주세요."
                        }
                        saving = false
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("add_save")
                    .semantics { contentDescription = "Save record" },
                colors = ButtonDefaults.buttonColors(containerColor = AppColors.Accent),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text(if (saving) "저장 중" else "저장하기")
            }
        }
    }
}

@Composable
private fun DayDetailCard(localDate: String, dayEntries: List<PhotoEntry>) {
    CardBlock {
        Text(localDate, style = MaterialTheme.typography.titleLarge, color = AppColors.Text)
        Spacer(Modifier.height(6.dp))
        Text("이 기기에 저장된 기록 ${dayEntries.size}개", color = AppColors.Text2)
        Spacer(Modifier.height(12.dp))
        dayEntries.forEach { entry ->
            RecordCard(entry)
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun DayDetailScreen(
    localDate: String,
    entries: List<PhotoEntry>,
    onBack: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            TextButton(onClick = onBack) {
                Text("Calendar로 돌아가기")
            }
            Text(localDate, style = MaterialTheme.typography.headlineMedium, color = AppColors.Text)
            Spacer(Modifier.height(8.dp))
            LocalBadge()
        }
        if (entries.isEmpty()) {
            item {
                EmptyCard("아직 기록이 없어요", "이 날짜에 남긴 기록이 없습니다.")
            }
        } else {
            item {
                Text("이 기기에 저장된 기록 ${entries.size}개", color = AppColors.Text2)
            }
            items(entries.sortedBy { it.createdAtMillis }) { entry ->
                RecordCard(entry)
            }
        }
    }
}

@Composable
private fun RecordCard(entry: PhotoEntry) {
    val context = LocalContext.current
    val thumbnailFile = remember(entry.thumbnailRelativePath) { File(context.filesDir, entry.thumbnailRelativePath) }
    var bitmap by remember(entry.thumbnailRelativePath) { mutableStateOf<android.graphics.Bitmap?>(null) }

    LaunchedEffect(thumbnailFile) {
        bitmap = withContext(Dispatchers.IO) {
            BitmapFactory.decodeFile(thumbnailFile.absolutePath)
        }
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = AppColors.Card),
        shape = RoundedCornerShape(18.dp),
    ) {
        Column(Modifier.padding(12.dp)) {
            bitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(14.dp)),
                    contentScale = ContentScale.Crop,
                )
                Spacer(Modifier.height(10.dp))
            }
            if (entry.note.isNotBlank()) {
                Text(entry.note, color = AppColors.Text)
            }
            entry.emotionTag?.let {
                Text(it.label, color = AppColors.Accent, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun ArchiveScreen(entries: List<PhotoEntry>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item {
            Text("Archive", style = MaterialTheme.typography.headlineMedium, color = AppColors.Text)
            Text("메모, 날짜로 검색하기는 다음 슬라이스에서 연결합니다.", color = AppColors.Text2)
        }
        items(entries.sortedByDescending { it.localDate }) { entry ->
            RecordCard(entry)
        }
    }
}

@Composable
private fun SettingsScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Text("Settings", style = MaterialTheme.typography.headlineMedium, color = AppColors.Text)
        }
        item {
            CardBlock {
                Text("이 기록은 현재 디바이스에만 저장됩니다.", fontWeight = FontWeight.Bold, color = AppColors.Text)
                Spacer(Modifier.height(8.dp))
                Text("앱 안에 사진 복사본과 메모를 보관해요. 서버 계정이나 외부 전송은 사용하지 않습니다.", color = AppColors.Text2)
            }
        }
        item {
            EmptyCard("백업 파일 내보내기", "다음 구현 슬라이스에서 로컬 파일로 내보내기를 연결합니다.")
        }
    }
}

@Composable
private fun LocalBadge() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(AppColors.GoodLight)
            .padding(horizontal = 12.dp, vertical = 7.dp)
            .testTag("local_storage_badge")
            .semantics { contentDescription = "Stored only on this phone" },
    ) {
        Text("내 폰에만 저장됨", color = AppColors.Good, style = MaterialTheme.typography.labelMedium)
    }
}

@Composable
private fun CardBlock(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = AppColors.Card),
        shape = RoundedCornerShape(18.dp),
        content = { Column(Modifier.padding(16.dp), content = content) },
    )
}

@Composable
private fun EmptyCard(title: String, body: String) {
    CardBlock {
        Text(title, fontWeight = FontWeight.Bold, color = AppColors.Text)
        Spacer(Modifier.height(6.dp))
        Text(body, color = AppColors.Text2)
    }
}

private fun LocalDate.toIsoDate(): String = format(DateTimeFormatter.ISO_LOCAL_DATE)

private fun String.toValidIsoDateOrNull(): String? = runCatching {
    LocalDate.parse(trim(), DateTimeFormatter.ISO_LOCAL_DATE).toIsoDate()
}.getOrNull()

private fun buildDateCellDescription(date: LocalDate, isToday: Boolean, count: Int): String {
    val todayText = if (isToday) ", today" else ""
    val countText = if (count == 0) "no records" else "$count record${if (count == 1) "" else "s"}"
    return "${date.toIsoDate()}$todayText, $countText"
}
