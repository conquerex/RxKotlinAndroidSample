package what.the.rxkotlin.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by jongkook on 2021.01.02
 */
abstract class BaseActivity : AppCompatActivity() {
    /*
    onCreate 메서드를 숨기고 새로운 추상 메서드를 제공 -onCreateBaseActivity
     */
    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateBaseActivity(savedInstanceState)
    }

    /*
    자식 클래스에서 onCreateBaseActivity를 재정의 하도록 해서
    모든 액티비티가 onCreate 메서드 내에서 구현해야 하는 것이 있을 경우
    BaseActivity의 onCreate 메서드 안에서 구현하고 나머지는 신경쓰지 않는다.
     */
    abstract fun onCreateBaseActivity(savedInstanceState: Bundle?)
}