from django.shortcuts import render
from rest_framework.generics import ListAPIView
from rest_framework.generics import RetrieveAPIView
from rest_framework.generics import CreateAPIView
from rest_framework.generics import DestroyAPIView
from rest_framework.generics import UpdateAPIView
from crud_operations.serializers import StoresSerializer, ProductsSerializer, BadgesSerializer, UsersSerializer, ItemsSerializer, ShoppingListsSerializer, ShoppingListItemsSerializer
from crud_operations.models import Stores, Products, Badges, Users, Items, ShoppingLists, ShoppingListItems

# Create your views here.
# Stores
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

# Products
class ListProductsAPIView(ListAPIView):
    """This endpoint lists all of the available products from the database"""
    queryset = Products.objects.all()
    serializer_class = ProductsSerializer

class RetrieveProductAPIView(RetrieveAPIView):
    """This endpoint retrieves a single product, specified by id"""
    queryset = Products.objects.all()
    serializer_class = ProductsSerializer

class CreateProductsAPIView(CreateAPIView):
    """This endpoint allows for creation of a product"""
    queryset = Products.objects.all()
    serializer_class = ProductsSerializer

class UpdateProductsAPIView(UpdateAPIView):
    """This endpoint allows for updating a specific product by passing in the id of the product to update"""
    queryset = Products.objects.all()
    serializer_class = ProductsSerializer

class DeleteProductsAPIView(DestroyAPIView):
    """This endpoint allows for deletion of a specific product from the database"""
    queryset = Products.objects.all()
    serializer_class = ProductsSerializer

# Badges
class ListBadgesAPIView(ListAPIView):
    """This endpoint lists all of the available badges from the database"""
    queryset = Badges.objects.all()
    serializer_class = BadgesSerializer

class RetrieveBadgeAPIView(RetrieveAPIView):
    """This endpoint retrieves a single badge, specified by id"""
    queryset = Badges.objects.all()
    serializer_class = BadgesSerializer

class CreateBadgesAPIView(CreateAPIView):
    """This endpoint allows for creation of a badge"""
    queryset = Badges.objects.all()
    serializer_class = BadgesSerializer

class UpdateBadgesAPIView(UpdateAPIView):
    """This endpoint allows for updating a specific badge by passing in the id of the badge to update"""
    queryset = Badges.objects.all()
    serializer_class = BadgesSerializer

class DeleteBadgesAPIView(DestroyAPIView):
    """This endpoint allows for deletion of a specific badge from the database"""
    queryset = Badges.objects.all()
    serializer_class = BadgesSerializer

# Users
class ListUsersAPIView(ListAPIView):
    """This endpoint lists all of the available users from the database"""
    queryset = Users.objects.all()
    serializer_class = UsersSerializer

class RetrieveUserAPIView(RetrieveAPIView):
    """This endpoint retrieves a single user, specified by id"""
    queryset = Users.objects.all()
    serializer_class = UsersSerializer

class CreateUsersAPIView(CreateAPIView):
    """This endpoint allows for creation of a user"""
    queryset = Users.objects.all()
    serializer_class = UsersSerializer

class UpdateUsersAPIView(UpdateAPIView):
    """This endpoint allows for updating a specific user by passing in the id of the user to update"""
    queryset = Users.objects.all()
    serializer_class = UsersSerializer

class DeleteUsersAPIView(DestroyAPIView):
    """This endpoint allows for deletion of a specific user from the database"""
    queryset = Users.objects.all()
    serializer_class = UsersSerializer

# Items
class ListItemsAPIView(ListAPIView):
    """This endpoint lists all of the available items for a specific product from the database"""
    queryset = Items.objects.all()
    serializer_class = ItemsSerializer
    def get_queryset(self):
        return super().get_queryset().filter(
            product_id=self.kwargs['pk']
        )
    
class RetrieveItemAPIView(RetrieveAPIView):
    """This endpoint retrieves a single item, specified by id"""
    queryset = Items.objects.all()
    serializer_class = ItemsSerializer

class CreateItemsAPIView(CreateAPIView):
    """This endpoint allows for creation of a item"""
    queryset = Items.objects.all()
    serializer_class = ItemsSerializer

class UpdateItemsAPIView(UpdateAPIView):
    """This endpoint allows for updating a specific item by passing in the id of the item to update"""
    queryset = Items.objects.all()
    serializer_class = ItemsSerializer

class DeleteItemsAPIView(DestroyAPIView):
    """This endpoint allows for deletion of a specific item from the database"""
    queryset = Items.objects.all()
    serializer_class = ItemsSerializer

# Shopping Lists
class ListShoppingListsAPIView(ListAPIView):
    """This endpoint lists all of the available shopping lists for a specific user from the database"""
    queryset = ShoppingLists.objects.all()
    serializer_class = ShoppingListsSerializer

    def get_queryset(self):
        return super().get_queryset().filter(
            user_id=self.kwargs['pk']
        )

class RetrieveShoppingListAPIView(RetrieveAPIView):
    """This endpoint retrieves a single shopping list, specified by id"""
    queryset = ShoppingLists.objects.all()
    serializer_class = ShoppingListsSerializer

class CreateShoppingListsAPIView(CreateAPIView):
    """This endpoint allows for creation of a shopping list"""
    queryset = ShoppingLists.objects.all()
    serializer_class = ShoppingListsSerializer

class UpdateShoppingListsAPIView(UpdateAPIView):
    """This endpoint allows for updating a specific shopping list by passing in the id of the list to update"""
    queryset = ShoppingLists.objects.all()
    serializer_class = ShoppingListsSerializer

class DeleteShoppingListsAPIView(DestroyAPIView):
    """This endpoint allows for deletion of a specific shopping list from the database"""
    queryset = ShoppingLists.objects.all()
    serializer_class = ShoppingListsSerializer

# Shopping List Items
class ListShoppingListItemsAPIView(ListAPIView):
    """This endpoint lists all of the available shopping list items for a list from the database"""
    queryset = ShoppingListItems.objects.all()
    serializer_class = ShoppingListItemsSerializer

    def get_queryset(self):
        return super().get_queryset().filter(
            shopping_list_id=self.kwargs['pk']
        )

class RetrieveShoppingListItemAPIView(RetrieveAPIView):
    """This endpoint retrieves a single shopping list item, specified by id"""
    queryset = ShoppingListItems.objects.all()
    serializer_class = ShoppingListItemsSerializer

class CreateShoppingListItemsAPIView(CreateAPIView):
    """This endpoint allows for creation of a shopping list item"""
    queryset = ShoppingListItems.objects.all()
    serializer_class = ShoppingListItemsSerializer

class UpdateShoppingListItemsAPIView(UpdateAPIView):
    """This endpoint allows for updating a specific list item by passing in the id of the list item to update"""
    queryset = ShoppingListItems.objects.all()
    serializer_class = ShoppingListItemsSerializer

class DeleteShoppingListItemsAPIView(DestroyAPIView):
    """This endpoint allows for deletion of a specific list item from the database"""
    queryset = ShoppingListItems.objects.all()
    serializer_class = ShoppingListItemsSerializer