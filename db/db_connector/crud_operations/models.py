from django.db import models
from django.contrib.postgres.fields import ArrayField
import datetime

# Create your models here.
class Stores(models.Model):
    store_name = models.CharField(max_length = 50)
    street_address = models.CharField(max_length = 50)
    city = models.CharField(max_length = 50)
    state = models.CharField(max_length = 2)
    zipcode = models.CharField(max_length = 5)

    def __str___(self):
        return self.store_name

class Products(models.Model):
    product_name = models.CharField(max_length = 50)
    category = models.CharField(max_length = 50)
    description = models.CharField(max_length = 100)
    image = models.CharField(max_length = 50)
    upvotes = models.IntegerField()
    tags = ArrayField(models.CharField(max_length = 50), blank=True, null=True)

    def __str___(self):
        return self.product_name

class Badges(models.Model):
    badge_name = models.CharField(max_length = 50)
    
    def __str___(self):
        return self.badge_name

class Users(models.Model):
    last_name = models.CharField(max_length = 50)
    first_name = models.CharField(max_length = 50)
    email_address = models.EmailField(max_length = 50)
    pword = models.CharField(max_length = 50)
    points = models.IntegerField()
    badge = models.ForeignKey(Badges, related_name = 'badge', on_delete = models.CASCADE)

    def __str___(self):
        return self.email_address


class Items(models.Model):
    store = models.ForeignKey(Stores, related_name = 'store', on_delete = models.CASCADE)
    product = models.ForeignKey(Products, related_name = 'item_product', on_delete = models.CASCADE)
    update_user = models.ForeignKey(Users, related_name = 'item_user', on_delete = models.CASCADE)
    price = models.IntegerField()
    is_on_sale = models.BooleanField()
    price_update_date = models.DateTimeField()

    def __str___(self):
        return self.product

class ShoppingLists(models.Model):
    list_name = models.CharField(max_length = 50)
    user = models.ForeignKey(Users, related_name = 'list_user', on_delete = models.CASCADE)

    def __str___(self):
        return self.list_name


class ShoppingListItems(models.Model):
    shopping_list = models.ForeignKey(ShoppingLists, related_name = 'shopping_list_name', on_delete = models.CASCADE)
    product = models.ForeignKey(Products, related_name = 'list_product', on_delete = models.CASCADE)

    def __str___(self):
        return self.product

