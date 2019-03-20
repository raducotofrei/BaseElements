package ch.rcotofrei.baseelements;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    BaseListElement<PersonDto> baseListElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseListElement = findViewById(R.id.listElements);
        baseListElement.setListController(new BaseListController<PersonDto>(){

            @Override
            public int getItemLayout(final int viewType) {
                return R.layout.list_person_cell;
            }

            @Override
            public BaseListViewHolder getViewHolder(final View itemView) {
                return new BaseListViewHolder(itemView) {
                    TextView personName;

                    @Override
                    public void initComponents(final View itemView) {
                        personName = itemView.findViewById(R.id.personName);
                    }

                    @Override
                    public void display(final PersonDto element) {
                        personName.setText(element.getName());
                    }
                };
            }
        });
        baseListElement.addItem(new PersonDto("Person1"));
        baseListElement.addItem(new PersonDto("Person2"));
        baseListElement.addItem(new PersonDto("Person3"));
        baseListElement.addItem(new PersonDto("Person4"));
    }
}
