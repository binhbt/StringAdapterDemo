package demo.com.basetest;

import com.fa.loader.FALoader;
import com.vn.fa.base.FaApplication;
import com.vn.fa.base.data.cache.CacheProviders;
import com.vn.fa.base.data.cache.rxcache.RxCacheAdapterFactory;
import com.vn.fa.base.util.FaLog;

/**
 * Created by leobui on 1/10/2018.
 */

public class TestApplication  extends FaApplication {
    public static FaApplication faApplication;
    public static RxCacheAdapterFactory cacheProviders;
    @Override
    public void onCreate() {
        super.onCreate();
        FALoader.type = FALoader.Type.FRESCO;
        FALoader.initialize(this);
        faApplication = this;
        FaLog.init(BuildConfig.DEBUG);
    }

    public static CacheProviders getCacheProviders(){
        if (cacheProviders == null){
            cacheProviders = new RxCacheAdapterFactory();
            cacheProviders.init(faApplication.getFilesDir());
        }
        return cacheProviders;
    }

    @Override
    public boolean isLogging() {
        return true;
    }
}
