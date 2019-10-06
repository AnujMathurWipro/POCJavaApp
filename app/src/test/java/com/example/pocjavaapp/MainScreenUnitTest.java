package com.example.pocjavaapp;

import com.example.pocjavaapp.models.Response;
import com.example.pocjavaapp.models.RowsItem;
import com.example.pocjavaapp.viewmodel.MainScreenViewModel;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MainScreenUnitTest {
    @Test
    public void testGetErrorMessage_Error() {
        MainScreenViewModel service = Mockito.spy(MainScreenViewModel.class);
        assertEquals("There was some problem with the request. Please try again.", service.getErrorMessage());
    }

    @Test
    public void testIsSuccessfulResponse_NoError() {
        MainScreenViewModel service = Mockito.spy(MainScreenViewModel.class);
        Response response = Mockito.mock(Response.class);
        ArrayList<RowsItem> responseList = new ArrayList<>();
        responseList.add(new RowsItem("", "", ""));
        responseList.add(new RowsItem("", "", ""));
        Mockito.when(response.getRows()).thenReturn(responseList);
        assertTrue(service.isSuccessful(response));
    }

    @Test
    public void testIsSuccessfulResponse_Error() {
        MainScreenViewModel service = Mockito.spy(MainScreenViewModel.class);
        Response response = Mockito.mock(Response.class);
        ArrayList<RowsItem> responseList = null;
        Mockito.when(response.getRows()).thenReturn(responseList);
        assertFalse(service.isSuccessful(response));
    }
}