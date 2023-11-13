from django.shortcuts import render
from rest_framework.generics import ListAPIView
from rest_framework.generics import RetrieveAPIView
from rest_framework.generics import CreateAPIView
from rest_framework.generics import DestroyAPIView
from rest_framework.generics import UpdateAPIView
from crud_operations.serializers import StoresSerializer
from crud_operations.models import Stores

# Create your views here.
class ListStoresAPIView(ListAPIView):
    """This endpoint lists all of the available stores from the database"""
    queryset = Stores.objects.all()
    serializer_class = StoresSerializer

class RetrieveStoreAPIView(RetrieveAPIView):
    """This endpoint retrieves a single store, specified by id"""
    queryset = Stores.objects.all()
    serializer_class = StoresSerializer

class CreateStoresAPIView(CreateAPIView):
    """This endpoint allows for creation of a store"""
    queryset = Stores.objects.all()
    serializer_class = StoresSerializer

class UpdateStoresAPIView(UpdateAPIView):
    """This endpoint allows for updating a specific store by passing in the id of the store to update"""
    queryset = Stores.objects.all()
    serializer_class = StoresSerializer

class DeleteStoresAPIView(DestroyAPIView):
    """This endpoint allows for deletion of a specific store from the database"""
    queryset = Stores.objects.all()
    serializer_class = StoresSerializer