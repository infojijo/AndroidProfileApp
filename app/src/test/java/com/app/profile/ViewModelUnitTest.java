package com.app.profile;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import network.IApiEndPoints;
import pojo.ApiResponse;
import pojo.ApiResponseDataSet;
import retrofit2.Call;
import viewmodel.ProfileViewModel;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class ViewModelUnitTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    ProfileViewModel profileViewModel;
    Map<String, String> headMap;

    @Mock
    IApiEndPoints mClient;

    @Mock
    Observer<ApiResponseDataSet> observer;

    @Mock Call<ApiResponse> response;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        profileViewModel = new ProfileViewModel();
        profileViewModel.getProfileLiveData().observeForever(observer);
        headMap = new HashMap<>();
        headMap.put("Content-Type", "application/json");
    }

    @Test
    public void testNull() {

        when(mClient.getProfile(headMap)).thenReturn(null);
        assertNotNull(profileViewModel.getProfileLiveData());
        assertNotNull(profileViewModel.getProfileLiveData().hasObservers());
    }

    @Test
    public void testNotNull() {
        when(mClient.getProfile(headMap)).thenReturn(response);
        assertNotNull(profileViewModel.getProfileLiveData());
        assertNotNull(profileViewModel.getProfileLiveData().hasObservers());
    }
}
