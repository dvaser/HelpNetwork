from django.urls import path
from . import views

urlpatterns = [
    path('menu', views.menu, name='afadMenu'),
    path('menu/ihtiyaçlar', views.ihtiyac, name='afadIhtiyac'),
    path('menu/yardimlar', views.yardimlar, name='afadInsan'),
    path('menu/yardim/noktalar', views.yardimNoktalari, name='afadYardimNoktalar'),
    path('menu/yardim/basvurular', views.yardimBasvurulari, name='afadYardimBasvurular'),
    
]
