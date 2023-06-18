from django.urls import path
from . import views

urlpatterns = [
    path('', views.home, name='home'),
    path('afad/', views.afadLogin, name='afadLogin'),
    path('belediye/', views.belediyeLogin, name='belediyeLogin'),
    path('ihtiyac/', views.ihtiyac, name='ihtiyac'),
    path('ihbar/', views.ihbar, name='ihbar'),
    path('yardim/', views.yardim, name='yardim'),
    path('map/', views.map, name='map'),
]
