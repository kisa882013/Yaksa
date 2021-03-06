package zlc.season.yaksaproject

import android.view.View
import kotlinx.android.synthetic.main.activity_example.*
import kotlinx.android.synthetic.main.header_item.view.*
import kotlinx.android.synthetic.main.list_item.view.*
import zlc.season.yaksa.YaksaItem
import zlc.season.yaksa.linear

class LinearExampleActivity : ExampleActivity() {

    override fun onChange(data: List<ExampleViewModel.ExampleData>?) {
        super.onChange(data)
        data?.let {
            example_rv.linear {
                item {
                    HeaderItem("This is a Header")
                }

                itemDsl(index = 0) {
                    xml(R.layout.header_item)
                    render {
                        it.header_item_tv.text = "This is a dsl Header"
                        it.setOnClickListener { toast("DSL Header Clicked") }
                    }
                }

                data.forEach { each ->
                    itemDsl {
                        xml(R.layout.list_item)
                        render {
                            it.list_item_tv.text = each.title
                        }
                        renderX { position, it ->
                            it.setOnClickListener { toast("Clicked $position") }
                        }
                    }
                }

                item {
                    ListItem("This is item too!")
                }
            }
        }
    }

    private class HeaderItem(val title: String) : YaksaItem {
        override fun render(position: Int, view: View) {
            view.header_item_tv.text = title
            view.setOnClickListener { }
        }

        override fun xml(): Int {
            return R.layout.header_item
        }
    }

    private class ListItem(val str: String) : YaksaItem {
        override fun render(position: Int, view: View) {
            view.list_item_tv.text = str
            view.setOnClickListener { }
        }

        override fun xml(): Int {
            return R.layout.list_item
        }
    }
}
