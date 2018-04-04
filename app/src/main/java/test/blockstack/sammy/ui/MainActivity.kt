package test.blockstack.sammy.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import test.blockstack.sammy.R

/**
 * Lots of TODO: pagination, loading handler, error handling, caching, data layer, service layer, ui layer, models, tests
 */
class MainActivity : AppCompatActivity(), NamespacesFragment.OnListFragmentInteractionListener {
    val DEFAULT_PROFILE_ID = "ryan.id";
    var namespacesFragment: NamespacesFragment? = NamespacesFragment.newInstance()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_namespaces -> {
                fragmentManager.beginTransaction().replace(R.id.fragment_container, NamespacesFragment.newInstance() ).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_name_details -> {
                goToNameDetails(DEFAULT_PROFILE_ID, false)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_namespaces
    }

    override fun onListFragmentInteraction(item: String) {
        goToNameDetails(item, true);
    }

    fun goToNameDetails(nameId: String, addToBackStack: Boolean) {
        var transaction = fragmentManager.beginTransaction().replace(R.id.fragment_container, NameDetailFragment.newInstance(nameId));
        if (addToBackStack) transaction.addToBackStack("tag")
        transaction.commit() ;
    }
}
