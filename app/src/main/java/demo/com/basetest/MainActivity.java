package demo.com.basetest;
//https://coinmarketcap.com/all/views/all/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vn.fa.base.data.net.FaRequest;
import com.vn.fa.base.util.FaLog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testUser();
    }

    public void testUser(){
        new BaseRequest()
                .path("ticker?limit=0")
                .params(new HashMap<>())
                .dataType(new TypeToken<String>() {}.getType())
                .callBack(new FaRequest.RequestCallBack<String>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onFinish(String result) {
                        if (result != null) {
                            //getListObject(result);
                            String okContent = result.replaceAll("24h_volume_usd", "d_volume_usd");
                            Type type = new TypeToken<List<Alt>>() {}.getType();
                            List<Alt> altList = new Gson().fromJson(okContent, type);
                            for (Alt alt:altList
                                 ) {
                                FaLog.e(alt.getName());
                            }
                        }
                    }
                })
                .doRequest();
    }
    private List<Object> getListObject(String content){
        List<Object> objectList = new ArrayList<>();
        Document doc = Jsoup.parse(content);
        Elements elements = doc.select("table tr");
        FaLog.e(elements.toString());
        if (elements.size() >1){
            elements.remove(0);
            for (Element tr:elements
                 ) {
                objectList.add(convertToObject(tr));
            }
        }
        return objectList;
    }
    private Object convertToObject(Element tr){
        Object object = new Object();
        return object;
    }
}
