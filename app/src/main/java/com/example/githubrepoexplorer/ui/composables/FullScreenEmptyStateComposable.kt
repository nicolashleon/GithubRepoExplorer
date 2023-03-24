import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CodeOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.githubrepoexplorer.R

@Composable
fun FullScreenEmptyState(message: String, onRetry: () -> Unit = {}) {
    GithubRepoExplorerTheme {
        Box(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    modifier = Modifier.size(48.dp),
                    imageVector = Icons.Default.CodeOff,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Text(text = message, style = typography.displaySmall, textAlign = TextAlign.Center)
            }
            Button(
                onClick = onRetry,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 24.dp),
            ) {
                Text(text = stringResource(id = R.string.txt_retry))
            }
        }
    }
}