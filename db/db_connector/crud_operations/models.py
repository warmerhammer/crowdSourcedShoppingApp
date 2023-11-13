from django.db import models
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