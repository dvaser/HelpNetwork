from django.shortcuts import render
import requests

nav = [
    {
        "name":'AFAD',
        "color":"#dc3545",
        "title":"AFAD Yetkili Girişi",
        "text":"İl bazlı yardım noktaları belirleme, malzeme/işçi ihtiyaç ve kesin ihbar noktaları girişi",
        "urlName":'afadLogin'
    },
    {
        "name":'BELEDİYE',
        "color":"#007bff",
        "title":"BELEDİYE Yetkili Girişi",
        "text":"İl bazlı malzeme girişi",
        "urlName":'belediyeLogin'
    },
    {
        "name":'İHBAR',
        "color":"#ffc107",
        "title":"Bireysel",
        "text":"Bulunduğunuz konumda acil durumdaysanız ve yetkililere ihtiyacınız varsa İHBAR HATTINA başvur",
        "urlName":'ihbar'
    },
    {
        "name":'İHTİYAÇ',
        "color":"#28a745",
        "title":"Bireysel",
        "text":"Bulunduğunuz konumda malzeme/işçi ihtiyacı varsa İHTİYAÇ HATTINA başvur",
        "urlName":'ihtiyac'
    },
    {
        "name":'YARDIM',
        "color":"#17a2b8",
        "title":"Bireysel",
        "text":"Yardımda bulunmak isteyen vatandaşlar, mesleki ya da ürün yardımında bulunabilir",
        "urlName":'yardim'
    }
]

# Create your views here.
def home(request):
    return render(request, 'pages/index.html', {"nav":nav})

def afadLogin(request):
    navs = nav.copy()
    navs.pop(0)
    return render(request, 'pages/afadLogin.html', {"nav":navs})

def belediyeLogin(request):
    navs = nav.copy()
    navs.pop(1)
    return render(request, 'pages/belediyeLogin.html', {"nav":navs})

def ihbar(request):
    if request.method == 'POST':
        print(request.POST) 
        name = request.POST.get('name')
        address = request.POST.get('address')
        lat = request.POST.get('latitude')
        lon = request.POST.get('longitude')

        json = {
            'location': f"{lat}-{lon}",
            'name':name,
            'productName':'',
            'number':1,
            'isHuman': True,    
            'phoneNumber': ''
        }

        url = "http://localhost:8080/v1/help/create"
        res = requests.post(url, json=json)

    navs = nav.copy()
    navs.pop(2)
    return render(request, 'pages/ihbar.html', {"nav":navs})

def ihtiyac(request):
    if request.method == 'POST':
        print(request.POST)
        name = request.POST.get('name')
        phone = request.POST.get('phone')
        product = request.POST.get('product')
        location = request.POST.get('location')

        json = {
            'location': location,
            'name':name,
            'productName':product,
            'number':2,
            'isHuman': False,
            'phoneNumber': str(phone)
        }

        url = "http://localhost:8080/v1/help/create"
        res = requests.post(url, json=json)


    url = 'http://localhost:8080/v1/point/points'
    response = requests.get(url)

    location = {}
    if response.status_code == 200:
        location = response.json()
        print(location)
    else:
        pass

    navs = nav.copy()
    navs.pop(3)
    return render(request, 'pages/ihtiyac.html', {"nav":navs, 'location':location})

def yardim(request):
    if request.method == 'POST':
        print(request.POST)
        name = request.POST.get('name')
        phone = request.POST.get('phone')
        job = request.POST.get('occupation')
        city = request.POST.get('city')

        json = {
            'location': str(city),
            'name':name,
            'productName':job,
            'number':3,
            'isHuman': True,    
            'phoneNumber': str(phone),
        }

        url = "http://localhost:8080/v1/help/create"
        res = requests.post(url, json=json)

    navs = nav.copy()
    navs.pop(4)
    return render(request, 'pages/yardim.html', {"nav":navs})

def map(request):
    url = 'http://localhost:8080/v1/help/helps'
    response = requests.get(url)

    data = {}
    if response.status_code == 200:
        data = response.json()
    else:
        pass

    print(data)

    url = 'http://localhost:8080/v1/point/points'
    _response = requests.get(url)

    helpMarkers = {}
    if _response.status_code == 200:
        helpMarkers = _response.json()
    else:
        pass

    return render(request, 'partials/_map.html', {"data":data, 'helpMarkers':helpMarkers})