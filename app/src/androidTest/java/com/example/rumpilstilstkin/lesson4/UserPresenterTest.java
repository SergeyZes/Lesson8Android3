package com.example.rumpilstilstkin.lesson4;

import android.support.test.runner.AndroidJUnit4;

import com.example.rumpilstilstkin.lesson4.presenters.home.UserPresenter;
import com.example.rumpilstilstkin.lesson4.presenters.home.UserView;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class UserPresenterTest {
    private static MockWebServer mockWebServer;


    @BeforeClass
    public static void setupServer() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
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
