package tk.sh.dev.apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tk.sh.dev.apps.apps.AppListFragment
import tk.sh.dev.apps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragmentTransaction = supportFragmentManager
            .beginTransaction()
            .replace(binding.container.id, AppListFragment())
        fragmentTransaction.commit()
    }
}