package com.example.rumpilstilstkin.lesson4;

import android.support.test.runner.AndroidJUnit4;

import com.example.rumpilstilstkin.lesson4.data.models.GithubUser;
import com.example.rumpilstilstkin.lesson4.data.rest.NetApiClient;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class NetApiClientTest {
    private static MockWebServer mockWebServer;


    @BeforeClass
    public static void setupServer() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(8080);

    }

    @AfterClass
    public static void shutdownServer() throws IOException {
        mockWebServer.shutdown();

    }


    @Test
    public void testWrongStatus() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(404).setBody("{}"));
        Observer<GithubUser> observer = mock(observerCls.class);

        NetApiClient netApiClient = NetApiClient.getInstance();
        netApiClient.getUser("SergeyrZes").subscribe(observer);
        verify(observer).onError(any(Throwable.class));

    }

    @Test
    public void testWrongJSON() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody("{}"));
        Observer<GithubUser> observer = mock(observerCls.class);

        NetApiClient netApiClient = NetApiClient.getInstance();
        netApiClient.getUser("SergeyZes").subscribe(observer);
        verify(observer).onError(any(Throwable.class));

    }


    class observerCls implements Observer<GithubUser> {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(GithubUser githubUser) {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

}
