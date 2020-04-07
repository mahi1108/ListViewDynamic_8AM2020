package cubex.mahesh.listviewdynamic

import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var status = ContextCompat.checkSelfPermission(
                this@MainActivity,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
if(status == PackageManager.PERMISSION_GRANTED){
    readFiles()
}else{
    ActivityCompat.requestPermissions(this@MainActivity,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            123)
}
    }

    override fun onRequestPermissionsResult(requestCode: Int,
             permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode,
                permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            readFiles()
        }else{
            finish()
        }
    }

    fun  readFiles(){
        val path = "/storage/emulated/0"
        val f:File = File(path)
        val files_list = f.list()
        var myadapter = ArrayAdapter<String>(this@MainActivity,
                android.R.layout.simple_list_item_multiple_choice,files_list)
        lview1.adapter = myadapter
    }
}
