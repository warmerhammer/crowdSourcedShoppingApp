from django.urls import path
from crud_operations import views

urlpatterns = [
    path("stores",views.ListStoresAPIView.as_view(),name="read_stores"),
    path("stores/<int:pk>", views.RetrieveStoreAPIView.as_view(), name="retrieve_store"),
    path("stores/create/", views.CreateStoresAPIView.as_view(),name="create_store"),
    path("stores/update/<int:pk>",views.UpdateStoresAPIView.as_view(),name="update_store"),
    path("stores/delete/<int:pk>",views.DeleteStoresAPIView.as_view(),name="delete_store"),

    path("products",views.ListProductsAPIView.as_view(),name="read_products"),
    path("products/<int:pk>", views.RetrieveProductAPIView.as_view(), name="retrieve_product"),
    path("products/create", views.CreateProductsAPIView.as_view(),name="create_product"),
    path("products/update/<int:pk>",views.UpdateProductsAPIView.as_view(),name="update_product"),
    path("products/delete/<int:pk>",views.DeleteProductsAPIView.as_view(),name="delete_product"),

    path("badges",views.ListBadgesAPIView.as_view(),name="read_badges"),
    path("badges/<int:pk>", views.RetrieveBadgeAPIView.as_view(), name="retrieve_badge"),
    path("badges/create", views.CreateBadgesAPIView.as_view(),name="create_badge"),
    path("badges/update/<int:pk>",views.UpdateBadgesAPIView.as_view(),name="update_badge"),
    path("badges/delete/<int:pk>",views.DeleteBadgesAPIView.as_view(),name="delete_badge"),

    path("users",views.ListUsersAPIView.as_view(),name="read_users"),
    path("users/<int:pk>", views.RetrieveUserAPIView.as_view(), name="retrieve_user"),
    path("users/create", views.CreateUsersAPIView.as_view(),name="create_user"),
    path("users/update/<int:pk>",views.UpdateUsersAPIView.as_view(),name="update_user"),
    path("users/delete/<int:pk>",views.DeleteUsersAPIView.as_view(),name="delete_user"),

    path("items/product/<int:pk>",views.ListItemsAPIView.as_view(),name="read_items_by_product"),
    path("items/<int:pk>", views.RetrieveItemAPIView.as_view(), name="retrieve_item"),
    path("items/create", views.CreateItemsAPIView.as_view(),name="create_item"),
    path("items/update/<int:pk>",views.UpdateItemsAPIView.as_view(),name="update_item"),
    path("items/delete/<int:pk>",views.DeleteItemsAPIView.as_view(),name="delete_item"),


    path("shoppinglists/user/<int:pk>",views.ListShoppingListsAPIView.as_view(),name="read_shopping_lists_by_user"),
    path("shoppinglists/<int:pk>", views.RetrieveShoppingListAPIView.as_view(), name="retrieve_shopping_list"),
    path("shoppinglists/create", views.CreateShoppingListsAPIView.as_view(),name="create_shopping_list"),
    path("shoppinglists/update/<int:pk>",views.UpdateShoppingListsAPIView.as_view(),name="update_shopping_list"),
    path("shoppinglists/delete/<int:pk>",views.DeleteShoppingListsAPIView.as_view(),name="delete_shopping_list"),

    path("shoppinglistitems/list/<int:pk>",views.ListShoppingListItemsAPIView.as_view(),name="read_shopping_list_items_by_list"),
    path("shoppinglistitems/<int:pk>", views.RetrieveShoppingListItemAPIView.as_view(), name="retrieve_shopping_list_item"),
    path("shoppinglistitems/create", views.CreateShoppingListItemsAPIView.as_view(),name="create_shopping_list_item"),
    path("shoppinglistitems/update/<int:pk>",views.UpdateShoppingListItemsAPIView.as_view(),name="update_shopping_list_item"),
    path("shoppinglistitems/delete/<int:pk>",views.DeleteShoppingListItemsAPIView.as_view(),name="delete_shopping_list_item"),
]