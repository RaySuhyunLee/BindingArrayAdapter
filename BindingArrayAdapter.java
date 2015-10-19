package net.raysuhyunlee.randomalarm;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Suhyun Lee on 2015-10-19.
 */
public class BindingArrayAdapter<T> extends ArrayAdapter{
    private ArrayList<T> list;
    private Context context;
    private int resource;
    private int variableId;

    public BindingArrayAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    public BindingArrayAdapter(Context context, int resource, ArrayList<T> list, int variableId) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;
        this.variableId = variableId;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    public void setVariableId(int variableId) {
        this.variableId = variableId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ViewDataBinding binding = DataBindingUtil.inflate(inflater, resource, parent, false);
            binding.setVariable(variableId, list.get(position));
            convertView = binding.getRoot();

            Log.d("getView", "Inflating new view: position at " + position + ", title: " + ((Item)list.get(position)).title);
        } else {
            Log.d("getView", "Reset variable");
            DataBindingUtil.bind(convertView).setVariable(variableId, list.get(position));
        }
        return convertView;
    }
}
