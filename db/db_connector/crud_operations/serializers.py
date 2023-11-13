from rest_framework import serializers
from crud_operations.models import Stores

class StoresSerializer(serializers.ModelSerializer):
    class Meta:
        model = Stores
        fields = "__all__"