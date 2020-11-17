package br.com.becare.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.becare.databinding.ActivitySearchBinding
import br.com.becare.entities.dtos.Hospital
import kotlinx.android.synthetic.main.activity_search.*

@Suppress(
    "UNCHECKED_CAST", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)
class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.show()

        with(entity_list_recycler_view) {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
        }

        val hospitalList: Array<Hospital> =
            intent.getSerializableExtra("HospitalEntity") as Array<Hospital>
        val entityType: String = intent.getStringExtra("EntityType")

        when(entityType) {
            "EmergencyRoom" -> page_name.text = "Pronto Socorros"
            else -> page_name.text = "Hospitais"
        }
        with(entityType) {
            if (entityType == "EmergencyRoom") hospitalList.filter {
                it.ps
            }
        }

        hospitalList.sortedBy { it.fila }
        entity_list_recycler_view.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        entity_list_recycler_view.adapter = ListAdapter(hospitalList)

    }
}
