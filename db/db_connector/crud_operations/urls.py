from django.urls import path
from crud_operations import views

urlpatterns = [
    path("stores/",views.ListStoresAPIView.as_view(),name="read_stores"),
    path("stores/<int:pk>/", views.RetrieveStoreAPIView.as_view(), name="retrieve_store"),
    path("stores/create/", views.CreateStoresAPIView.as_view(),name="create_store"),
    path("stores/update/<int:pk>/",views.UpdateStoresAPIView.as_view(),name="update_store"),
    path("stores/delete/<int:pk>/",views.DeleteStoresAPIView.as_view(),name="delete_store")
]