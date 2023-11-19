from rest_framework import serializers
from crud_operations.models import Stores, Products, Badges, Users, Items, ShoppingLists, ShoppingListItems

class StoresSerializer(serializers.ModelSerializer):
    class Meta:
        model = Stores
        fields = "__all__"

class ProductsSerializer(serializers.ModelSerializer):
    class Meta:
        model = Products
        fields = "__all__"

class BadgesSerializer(serializers.ModelSerializer):
    class Meta:
        model = Badges
        fields = "__all__"

class UsersSerializer(serializers.ModelSerializer):
    class Meta:
        model = Users
        fields = "__all__"

class ItemsSerializer(serializers.ModelSerializer):
    class Meta:
        model = Items
        fields = "__all__"

class ShoppingListsSerializer(serializers.ModelSerializer):
    class Meta:
        model = ShoppingLists
        fields = "__all__"

class ShoppingListItemsSerializer(serializers.ModelSerializer):
    class Meta:
        model = ShoppingListItems
        fields = "__all__"