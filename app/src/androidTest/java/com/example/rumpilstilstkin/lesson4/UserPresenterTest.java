package com.example.rumpilstilstkin.lesson4;

import android.support.test.runner.AndroidJUnit4;

import com.example.rumpilstilstkin.lesson4.data.models.GithubUser;
import com.example.rumpilstilstkin.lesson4.data.models.RepsModel;
import com.example.rumpilstilstkin.lesson4.data.rest.NetApiClient;
import com.example.rumpilstilstkin.lesson4.data.rest.NetApiClientInterface;
import com.example.rumpilstilstkin.lesson4.presenters.home.UserPresenter;
import com.example.rumpilstilstkin.lesson4.presenters.home.UserView;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import javax.annotation.meta.When;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class UserPresenterTest {
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
        UserPresenter userPresenter = new UserPresenter();
        UserView view = mock(UserViewCls.class);

        userPresenter.attachView(view);
        verify(view).showError(any(Throwable.class));

    }

    @Test
    public void testWrongJSON() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody("{}"));
        UserPresenter userPresenter = new UserPresenter();
        UserView view = mock(UserViewCls.class);

        userPresenter.attachView(view);
        verify(view).showError(any(Throwable.class));

    }

    @Test
    public void testChangeNetApiClientInPresenter () {
        NetApiClient netApiClient = mock(NetApiClient.class);

        when(netApiClient.getUser(anyString())).thenReturn(new Observable<GithubUser>() {
            @Override
            protected void subscribeActual(Observer<? super GithubUser> observer) {
              //
            }
        });


        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody("{}"));
        UserPresenter userPresenter = new UserPresenter(true);

        DaggerNetModuleComponent.builder().daggerNetModule(new DaggerNetModule(netApiClient)).build().injects(userPresenter);


        UserView view = mock(UserViewCls.class);

        userPresenter.attachView(view);
        verify(netApiClient).getUser(anyString());

    }

    class NetApiClientInterfaceCls implements NetApiClientInterface {

        @Override
        public Observable<GithubUser> getUser(String user) {
            return null;
        }

        @Override
        public Flowable<List<RepsModel>> getReps() {
            return null;
        }
    }


    class UserViewCls implements UserView {

        @Override
        public void showName(String name) {

        }

        @Override
        public void showImage(String imageUrl) {

        }

        @Override
        public void showError(Throwable e) {

        }

        @Override
        public void showLoading() {

        }

        @Override
        public void hideLoading() {

        }
    }


}
