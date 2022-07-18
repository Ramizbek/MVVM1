package ramizbek.aliyev.mvvm

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import ramizbek.aliyev.mvvm.databinding.ActivityMainBinding
import ramizbek.aliyev.mvvm.models.MyData
import ramizbek.aliyev.mvvm.repository.MyDataRepository
import ramizbek.aliyev.mvvm.utils.Status
import ramizbek.aliyev.mvvm.viewmodel.MyViewModel
import ramizbek.aliyev.mvvm.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myData = MyData()
        val myDataRepository = MyDataRepository(myData)
        myViewModel =
            ViewModelProvider(this, ViewModelFactory(myDataRepository))[MyViewModel::class.java]

        loadTv()
        editLoad()

    }

    private fun editLoad() {
        binding.edtInput.addTextChangedListener {
            myViewModel.loadString(it.toString())
        }
    }

    private fun loadTv() {
        myViewModel.getData().observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.tvInfo.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvInfo.text = it.data
                    binding.tvInfo.visibility = View.VISIBLE
                    binding.tvInfo.setTextColor(Color.BLACK)
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvInfo.visibility = View.VISIBLE
                    binding.tvInfo.text = it.message
                    binding.tvInfo.setTextColor(Color.RED)
                }

            }
        }
    }
}