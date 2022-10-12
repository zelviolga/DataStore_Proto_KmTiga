package coding.withze.ds_proto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import coding.withze.ds_proto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModeluser : UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModeluser = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.saveBtn.setOnClickListener {
            val name = binding.name.text.toString()
            viewModeluser.editData(name)
        }

        viewModeluser.dataUser.observe(this,{
            binding.resultName.text = it.name
        })

        binding.clearBtn.setOnClickListener {
            viewModeluser.clearData()
        }

    }
}