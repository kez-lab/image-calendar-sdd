package com.kezlab.imagecalendar.app

import android.content.Context
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
import androidx.compose.material3.AlertDialog
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
import com.kezlab.imagecalendar.core.repository.UpdatePhotoEntryInput
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
    val preferences = remember(context) {
        context.getSharedPreferences("image_calendar_preferences", Context.MODE_PRIVATE)
    }
    var onboardingComplete by remember {
        mutableStateOf(preferences.getBoolean("onboarding_complete", false))
    }

    if (!onboardingComplete) {
        OnboardingScreen(
            onStartRecord = {
                preferences.edit().putBoolean("onboarding_complete", true).apply()
                onboardingComplete = true
                currentTab = AppTab.Add
            },
            onSkip = {
                preferences.edit().putBoolean("onboarding_complete", true).apply()
                onboardingComplete = true
                currentTab = AppTab.Calendar
            },
        )
    } else {
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
                        repository = repository,
                        onBack = {
                            dayDetailDate = null
                            currentTab = AppTab.Calendar
                        },
                        onEntryUpdated = { updated ->
                            selectedDate = updated.localDate
                            dayDetailDate = updated.localDate
                        },
                    )
                } else when (currentTab) {
                    AppTab.Calendar -> CalendarScreen(
                        entries = entries,
                        selectedDate = selectedDate,
                        onAdd = {
                            selectedDate = it
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

                    AppTab.Archive -> ArchiveScreen(
                        entries = entries,
                        onAdd = {
                            selectedDate = LocalDate.now().toIsoDate()
                            currentTab = AppTab.Add
                        },
                        onOpenDay = { date ->
                            selectedDate = date
                            dayDetailDate = date
                        },
                    )

                    AppTab.Settings -> SettingsScreen(
                        entries = entries,
                        repository = repository,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun OnboardingScreen(
    onStartRecord: () -> Unit,
    onSkip: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Bg)
            .padding(24.dp)
            .semantics { testTagsAsResourceId = true },
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        item {
            Spacer(Modifier.height(24.dp))
            Text("Image Calendar", style = MaterialTheme.typography.headlineLarge, color = AppColors.Text)
            Spacer(Modifier.height(8.dp))
            Text("내 폰 안에만 조용히 쌓이는 하루 사진 캘린더", color = AppColors.Text2)
        }
        item {
            OnboardingValueCard(
                step = "1",
                title = "하루를 사진으로 기록하세요",
                body = "사진 한 장과 짧은 메모로 오늘의 순간을 빠르게 남깁니다.",
            )
        }
        item {
            OnboardingValueCard(
                step = "2",
                title = "날짜별로 캘린더에 정리하세요",
                body = "기록한 날짜는 캘린더에서 점으로 표시되어 다시 찾기 쉽습니다.",
            )
        }
        item {
            OnboardingValueCard(
                step = "3",
                title = "내 폰에만 저장되는 프라이빗 기록",
                body = "계정 없이 사용하고, 사진과 메모는 현재 기기 안에 보관됩니다.",
            )
        }
        item {
            Button(
                onClick = onStartRecord,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("onboarding_start_record")
                    .semantics { contentDescription = "Start first record" },
                colors = ButtonDefaults.buttonColors(containerColor = AppColors.Accent),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text("첫 기록 만들기")
            }
            TextButton(
                onClick = onSkip,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("onboarding_skip")
                    .semantics { contentDescription = "Skip to calendar" },
            ) {
                Text("건너뛰고 캘린더 보기")
            }
        }
    }
}

@Composable
private fun OnboardingValueCard(step: String, title: String, body: String) {
    CardBlock {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(AppColors.AccentLight),
                contentAlignment = Alignment.Center,
            ) {
                Text(step, color = AppColors.Accent, fontWeight = FontWeight.Bold)
            }
            Column {
                Text(title, fontWeight = FontWeight.Bold, color = AppColors.Text)
                Spacer(Modifier.height(4.dp))
                Text(body, color = AppColors.Text2)
            }
        }
    }
}

@Composable
private fun CalendarScreen(
    entries: List<PhotoEntry>,
    selectedDate: String,
    onAdd: (String) -> Unit,
    onDateSelected: (String) -> Unit,
    onOpenDay: (String) -> Unit,
) {
    var month by remember { mutableStateOf(YearMonth.now()) }
    val today = LocalDate.now()
    val entryCounts = entries.groupingBy { it.localDate }.eachCount()
    val selectedEntries = entries.filter { it.localDate == selectedDate }

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
                onClick = { onAdd(today.toIsoDate()) },
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
            if (selectedEntries.isEmpty()) {
                EmptyActionCard(
                    title = "아직 기록이 없어요",
                    body = "$selectedDate 에 남긴 기록이 없습니다.",
                    actionLabel = "이 날짜에 기록 추가하기",
                    testTag = "calendar_empty_add",
                    onAction = { onAdd(selectedDate) },
                )
            } else {
                DayDetailCard(selectedDate, selectedEntries)
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
    repository: PhotoEntryRepository,
    onBack: () -> Unit,
    onEntryUpdated: (PhotoEntry) -> Unit,
) {
    val scope = rememberCoroutineScope()
    var editingEntry by remember { mutableStateOf<PhotoEntry?>(null) }
    var deletingEntry by remember { mutableStateOf<PhotoEntry?>(null) }
    var actionError by remember { mutableStateOf<String?>(null) }

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
            actionError?.let {
                Spacer(Modifier.height(8.dp))
                Text(it, color = AppColors.Danger)
            }
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
                RecordCard(
                    entry = entry,
                    onEdit = {
                        actionError = null
                        editingEntry = entry
                    },
                    onDelete = {
                        actionError = null
                        deletingEntry = entry
                    },
                )
            }
        }
    }

    editingEntry?.let { entry ->
        EditRecordDialog(
            entry = entry,
            onDismiss = { editingEntry = null },
            onSave = { input ->
                scope.launch {
                    runCatching {
                        withContext(Dispatchers.IO) {
                            repository.updatePhotoEntry(entry.id, input)
                        }
                    }.onSuccess { updated ->
                        editingEntry = null
                        onEntryUpdated(updated)
                    }.onFailure {
                        actionError = "기록을 수정하지 못했어요. 다시 시도해주세요."
                    }
                }
            },
        )
    }

    deletingEntry?.let { entry ->
        DeleteRecordDialog(
            entry = entry,
            onDismiss = { deletingEntry = null },
            onConfirm = {
                scope.launch {
                    runCatching {
                        withContext(Dispatchers.IO) {
                            repository.deletePhotoEntry(entry.id)
                        }
                    }.onSuccess {
                        deletingEntry = null
                    }.onFailure {
                        actionError = "기록을 삭제하지 못했어요. 다시 시도해주세요."
                    }
                }
            },
        )
    }
}

@Composable
private fun RecordCard(
    entry: PhotoEntry,
    onEdit: (() -> Unit)? = null,
    onDelete: (() -> Unit)? = null,
) {
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
                    contentDescription = "Stored record photo",
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
            if (onEdit != null || onDelete != null) {
                Spacer(Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    onEdit?.let {
                        TextButton(
                            onClick = it,
                            modifier = Modifier
                                .testTag("record_edit_${entry.id}")
                                .semantics { contentDescription = "Edit record" },
                        ) {
                            Text("수정")
                        }
                    }
                    onDelete?.let {
                        TextButton(
                            onClick = it,
                            modifier = Modifier
                                .testTag("record_delete_${entry.id}")
                                .semantics { contentDescription = "Delete record" },
                        ) {
                            Text("삭제", color = AppColors.Danger)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EditRecordDialog(
    entry: PhotoEntry,
    onDismiss: () -> Unit,
    onSave: (UpdatePhotoEntryInput) -> Unit,
) {
    var localDate by remember(entry.id) { mutableStateOf(entry.localDate) }
    var note by remember(entry.id) { mutableStateOf(entry.note) }
    var emotion by remember(entry.id) { mutableStateOf(entry.emotionTag) }
    var error by remember(entry.id) { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("기록 수정") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("사진은 유지하고 날짜, 메모, 감정 태그만 수정합니다.", color = AppColors.Text2)
                TextField(
                    value = localDate,
                    onValueChange = { localDate = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("edit_local_date")
                        .semantics { contentDescription = "Edit record date" },
                    singleLine = true,
                    label = { Text("날짜") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = AppColors.Bg2,
                        unfocusedContainerColor = AppColors.Bg2,
                    ),
                )
                TextField(
                    value = note,
                    onValueChange = { note = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("edit_note")
                        .semantics { contentDescription = "Edit note" },
                    label = { Text("메모") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = AppColors.Bg2,
                        unfocusedContainerColor = AppColors.Bg2,
                    ),
                )
                EmotionTag.entries.chunked(3).forEach { rowTags ->
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        rowTags.forEach { tag ->
                            FilterChip(
                                modifier = Modifier
                                    .testTag("edit_emotion_${tag.name.lowercase()}")
                                    .semantics { contentDescription = "Edit emotion ${tag.label}" },
                                selected = emotion == tag,
                                onClick = { emotion = if (emotion == tag) null else tag },
                                label = { Text(tag.label) },
                            )
                        }
                    }
                }
                error?.let {
                    Text(it, color = AppColors.Danger)
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val normalizedDate = localDate.toValidIsoDateOrNull()
                    if (normalizedDate == null) {
                        error = "날짜는 YYYY-MM-DD 형식으로 입력해주세요."
                        return@Button
                    }
                    error = null
                    onSave(
                        UpdatePhotoEntryInput(
                            localDate = normalizedDate,
                            note = note,
                            emotionTag = emotion,
                        ),
                    )
                },
                modifier = Modifier
                    .testTag("edit_save")
                    .semantics { contentDescription = "Save edited record" },
                colors = ButtonDefaults.buttonColors(containerColor = AppColors.Accent),
            ) {
                Text("저장")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier
                    .testTag("edit_cancel")
                    .semantics { contentDescription = "Cancel edit" },
            ) {
                Text("취소")
            }
        },
    )
}

@Composable
private fun DeleteRecordDialog(
    entry: PhotoEntry,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("이 기록을 삭제할까요?") },
        text = {
            Text(
                text = "${entry.localDate} 기록의 사진과 메모가 이 기기에서 삭제돼요. 삭제 후에는 복구할 수 없어요.",
                color = AppColors.Text2,
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                modifier = Modifier
                    .testTag("delete_confirm")
                    .semantics { contentDescription = "Confirm delete record" },
                colors = ButtonDefaults.buttonColors(containerColor = AppColors.Danger),
            ) {
                Text("삭제")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier
                    .testTag("delete_cancel")
                    .semantics { contentDescription = "Cancel delete record" },
            ) {
                Text("취소")
            }
        },
    )
}

@Composable
private fun ArchiveScreen(
    entries: List<PhotoEntry>,
    onAdd: () -> Unit,
    onOpenDay: (String) -> Unit,
) {
    var query by remember { mutableStateOf("") }
    var selectedEmotion by remember { mutableStateOf<EmotionTag?>(null) }
    val filteredEntries = remember(entries, query, selectedEmotion) {
        entries
            .filter { it.matchesArchiveQuery(query) }
            .filter { selectedEmotion == null || it.emotionTag == selectedEmotion }
            .sortedWith(compareByDescending<PhotoEntry> { it.localDate }.thenByDescending { it.createdAtMillis })
    }
    val monthGroups = remember(filteredEntries) {
        filteredEntries.groupBy { it.localDate.take(7) }.toSortedMap(reverseOrder())
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Text("Archive", style = MaterialTheme.typography.headlineMedium, color = AppColors.Text)
            Text("저장된 기록을 월별로 모아보고 빠르게 찾아요.", color = AppColors.Text2)
        }
        item {
            TextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text("메모, 날짜로 검색하기") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("archive_search")
                    .semantics { contentDescription = "Archive memo and date search" },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = AppColors.Card,
                    unfocusedContainerColor = AppColors.Card,
                ),
            )
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                EmotionTag.entries.forEach { tag ->
                    FilterChip(
                        modifier = Modifier
                            .testTag("archive_emotion_${tag.name.lowercase()}")
                            .semantics { contentDescription = "Archive emotion ${tag.label}" },
                        selected = selectedEmotion == tag,
                        onClick = { selectedEmotion = if (selectedEmotion == tag) null else tag },
                        label = { Text(tag.label) },
                    )
                }
            }
        }
        if (entries.isEmpty()) {
            item {
                EmptyActionCard(
                    title = "아직 저장된 기록이 없어요",
                    body = "사진 한 장으로 하루를 캘린더에 남겨보세요.",
                    actionLabel = "첫 기록 만들기",
                    testTag = "archive_empty_add",
                    onAction = onAdd,
                )
            }
        } else if (filteredEntries.isEmpty()) {
            item {
                EmptyActionCard(
                    title = "검색 결과가 없어요",
                    body = "다른 메모, 날짜, 감정 태그로 다시 찾아보세요.",
                    actionLabel = "필터 지우기",
                    testTag = "archive_clear_filters",
                    onAction = {
                        query = ""
                        selectedEmotion = null
                    },
                )
            }
        } else {
            monthGroups.forEach { (month, records) ->
                item(key = "archive_month_$month") {
                    Text(month.replace("-", "."), fontWeight = FontWeight.Bold, color = AppColors.Text)
                    Text("이 기기에 저장된 기록 ${records.size}개", color = AppColors.Text2)
                }
                items(records.chunked(2), key = { row -> row.joinToString { it.id } }) { rowEntries ->
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                        rowEntries.forEach { entry ->
                            ArchiveRecordCard(
                                entry = entry,
                                modifier = Modifier.weight(1f),
                                onClick = { onOpenDay(entry.localDate) },
                            )
                        }
                        if (rowEntries.size == 1) {
                            Spacer(Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsScreen(
    entries: List<PhotoEntry>,
    repository: PhotoEntryRepository,
) {
    val scope = rememberCoroutineScope()
    var exportStatus by remember { mutableStateOf<String?>(null) }
    var deleteStatus by remember { mutableStateOf<String?>(null) }
    var showDeleteAllDialog by remember { mutableStateOf(false) }
    var deleting by remember { mutableStateOf(false) }
    var exporting by remember { mutableStateOf(false) }
    val exportLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/zip"),
        onResult = { uri ->
            if (uri == null) {
                exportStatus = "백업 파일 내보내기를 취소했어요."
                return@rememberLauncherForActivityResult
            }
            exporting = true
            exportStatus = null
            scope.launch {
                runCatching {
                    withContext(Dispatchers.IO) {
                        repository.exportBackup(uri)
                    }
                }.onSuccess { count ->
                    exportStatus = "백업 파일을 저장했어요. 기록 $count 개가 포함됐습니다."
                }.onFailure {
                    exportStatus = "백업 파일을 만들지 못했어요. 다시 시도해주세요."
                }
                exporting = false
            }
        },
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Text("Settings", style = MaterialTheme.typography.headlineMedium, color = AppColors.Text)
            Text("로컬 저장과 데이터 관리를 확인합니다.", color = AppColors.Text2)
        }
        item {
            CardBlock {
                Text("이 기록은 현재 디바이스에만 저장됩니다.", fontWeight = FontWeight.Bold, color = AppColors.Text)
                Spacer(Modifier.height(8.dp))
                Text("앱 안에 사진 복사본과 메모를 보관해요. 계정 없이 사용할 수 있고 외부 전송은 사용하지 않습니다.", color = AppColors.Text2)
                Spacer(Modifier.height(8.dp))
                LocalBadge()
            }
        }
        item {
            CardBlock {
                Text("백업 파일 내보내기", fontWeight = FontWeight.Bold, color = AppColors.Text)
                Spacer(Modifier.height(8.dp))
                Text("내 기록과 사진 복사본을 zip 파일로 저장합니다. 파일 보관 위치는 직접 선택합니다.", color = AppColors.Text2)
                Spacer(Modifier.height(12.dp))
                Button(
                    enabled = !exporting,
                    onClick = {
                        exportLauncher.launch("image-calendar-backup-${LocalDate.now().toIsoDate()}.zip")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("settings_backup_export")
                        .semantics { contentDescription = "Export backup file" },
                    colors = ButtonDefaults.buttonColors(containerColor = AppColors.Accent),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Text(if (exporting) "백업 파일 만드는 중" else "백업 파일 내보내기")
                }
                exportStatus?.let {
                    Spacer(Modifier.height(8.dp))
                    Text(it, color = AppColors.Text2)
                }
            }
        }
        item {
            CardBlock {
                Text("백업에서 복원하기", fontWeight = FontWeight.Bold, color = AppColors.Text3)
                Spacer(Modifier.height(6.dp))
                Text("복원은 기존 기록과 충돌할 수 있어 다음 버전에서 안전하게 제공합니다.", color = AppColors.Text2)
                Spacer(Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(AppColors.Bg2)
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .testTag("settings_restore_coming_soon")
                        .semantics { contentDescription = "Restore coming soon" },
                ) {
                    Text("준비 중", color = AppColors.Text2)
                }
            }
        }
        item {
            CardBlock {
                Text("앱 정보", fontWeight = FontWeight.Bold, color = AppColors.Text)
                Spacer(Modifier.height(8.dp))
                Text("Image Calendar 0.1.0", color = AppColors.Text2)
                Text("저장된 기록 ${entries.size}개", color = AppColors.Text2)
            }
        }
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = AppColors.Card),
                shape = RoundedCornerShape(18.dp),
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("위험 영역", fontWeight = FontWeight.Bold, color = AppColors.Danger)
                    Spacer(Modifier.height(8.dp))
                    Text("전체 데이터 삭제는 이 기기에 저장된 기록, 사진 복사본, 메모, 감정 태그를 삭제합니다.", color = AppColors.Text2)
                    Spacer(Modifier.height(12.dp))
                    Button(
                        enabled = !deleting && entries.isNotEmpty(),
                        onClick = {
                            deleteStatus = null
                            showDeleteAllDialog = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("settings_delete_all")
                            .semantics { contentDescription = "Delete all local records" },
                        colors = ButtonDefaults.buttonColors(containerColor = AppColors.Danger),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Text(if (deleting) "삭제 중" else "전체 데이터 삭제")
                    }
                    deleteStatus?.let {
                        Spacer(Modifier.height(8.dp))
                        Text(it, color = AppColors.Text2)
                    }
                    if (entries.isEmpty()) {
                        Spacer(Modifier.height(8.dp))
                        Text("삭제할 기록이 없습니다.", color = AppColors.Text2)
                    }
                }
            }
        }
    }

    if (showDeleteAllDialog) {
        DeleteAllDataDialog(
            entryCount = entries.size,
            onDismiss = { showDeleteAllDialog = false },
            onConfirm = {
                showDeleteAllDialog = false
                deleting = true
                scope.launch {
                    runCatching {
                        withContext(Dispatchers.IO) {
                            repository.deleteAllUserData()
                        }
                    }.onSuccess { count ->
                        deleteStatus = "이 기기의 기록 $count 개를 삭제했어요."
                    }.onFailure {
                        deleteStatus = "전체 데이터를 삭제하지 못했어요. 다시 시도해주세요."
                    }
                    deleting = false
                }
            },
        )
    }
}

@Composable
private fun ArchiveRecordCard(
    entry: PhotoEntry,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    val thumbnailFile = remember(entry.thumbnailRelativePath) { File(context.filesDir, entry.thumbnailRelativePath) }
    var bitmap by remember(entry.thumbnailRelativePath) { mutableStateOf<android.graphics.Bitmap?>(null) }

    LaunchedEffect(thumbnailFile) {
        bitmap = withContext(Dispatchers.IO) {
            BitmapFactory.decodeFile(thumbnailFile.absolutePath)
        }
    }

    Card(
        modifier = modifier
            .testTag("archive_record_${entry.id}")
            .semantics { contentDescription = "Archive record ${entry.localDate}" }
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = AppColors.Card),
        shape = RoundedCornerShape(18.dp),
    ) {
        Column(Modifier.padding(10.dp)) {
            bitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Archive record photo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(128.dp)
                        .clip(RoundedCornerShape(14.dp)),
                    contentScale = ContentScale.Crop,
                )
                Spacer(Modifier.height(8.dp))
            }
            Text(entry.localDate, fontWeight = FontWeight.Bold, color = AppColors.Text)
            if (entry.note.isNotBlank()) {
                Text(entry.note, color = AppColors.Text2)
            }
            entry.emotionTag?.let {
                Text(it.label, color = AppColors.Accent, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun DeleteAllDataDialog(
    entryCount: Int,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("전체 데이터를 삭제할까요?") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("이 기기에 저장된 기록 $entryCount 개와 사진 복사본, 메모, 감정 태그를 삭제합니다.", color = AppColors.Text2)
                Text("백업 파일이 없다면 삭제 후에는 복구할 수 없어요.", color = AppColors.Danger)
                Text("온보딩 완료 상태는 유지됩니다.", color = AppColors.Text2)
            }
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                modifier = Modifier
                    .testTag("delete_all_confirm")
                    .semantics { contentDescription = "Confirm delete all local data" },
                colors = ButtonDefaults.buttonColors(containerColor = AppColors.Danger),
            ) {
                Text("전체 삭제")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier
                    .testTag("delete_all_cancel")
                    .semantics { contentDescription = "Cancel delete all local data" },
            ) {
                Text("취소")
            }
        },
    )
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
private fun EmptyActionCard(
    title: String,
    body: String,
    actionLabel: String,
    testTag: String,
    onAction: () -> Unit,
) {
    CardBlock {
        Text(title, fontWeight = FontWeight.Bold, color = AppColors.Text)
        Spacer(Modifier.height(6.dp))
        Text(body, color = AppColors.Text2)
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = onAction,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag(testTag),
            colors = ButtonDefaults.buttonColors(containerColor = AppColors.Accent),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(actionLabel)
        }
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

private fun PhotoEntry.matchesArchiveQuery(query: String): Boolean {
    val trimmed = query.trim()
    if (trimmed.isBlank()) return true

    val normalizedDateQuery = trimmed.replace(".", "-")
    val dateMatches = when {
        Regex("""\d{4}-\d{2}-\d{2}""").matches(normalizedDateQuery) -> localDate == normalizedDateQuery
        Regex("""\d{4}-\d{2}""").matches(normalizedDateQuery) -> localDate.startsWith(normalizedDateQuery)
        else -> false
    }

    return dateMatches || note.contains(trimmed, ignoreCase = true)
}

private fun buildDateCellDescription(date: LocalDate, isToday: Boolean, count: Int): String {
    val todayText = if (isToday) ", today" else ""
    val countText = if (count == 0) "no records" else "$count record${if (count == 1) "" else "s"}"
    return "${date.toIsoDate()}$todayText, $countText"
}
