from django.shortcuts import render
import requests

# Create your views here.
def menu(request):
    return render(request, 'afad/afadMenu.html')

def ihtiyac(request):

    url = 'http://localhost:8080/v1/assistance/assistances'
    response = requests.get(url)

    stok = {}
    if response.status_code == 200:
        stok = response.json()
        print(stok)
    else:
        pass

    url = 'http://localhost:8080/v1/point/points'
    _response = requests.get(url)

    need = {}
    if _response.status_code == 200:
        need = _response.json()
        print(need)
    else:
        pass

    return render(request, 'afad/ihtiyacYardim.html', {'stoks':stok, 'needs':need})

def yardimlar(request):
    url = 'http://localhost:8080/v1/help/helps'
    response = requests.get(url)

    humans = {}
    if response.status_code == 200:
        humans = response.json()
        print(humans)
    else:
        pass

    return render(request, 'afad/insanYardim.html', {'humans':humans})

def yardimNoktalari(request):
    if request.method == 'POST':
        print(request.POST) 
        lat = request.POST.get('latitude')
        lon = request.POST.get('longitude')

        json = {
            'location': f"{lat}-{lon}"
        }
        
        url = "http://localhost:8080/v1/point/create"
        res = requests.post(url, json=json)

    url = 'http://localhost:8080/v1/point/points'
    response = requests.get(url)

    data = {}
    if response.status_code == 200:
        data = response.json()
    else:
        pass
    return render(request, 'afad/yardimNoktalari.html', {"data":data})

def yardimBasvurulari(request):
    url = 'http://localhost:8080/v1/help/helps'
    response = requests.get(url)

    humans = {}
    if response.status_code == 200:
        humans = response.json()
        print(humans)
    else:
        pass
    return render(request, "afad/yardimBasvurulari.html", {"humans":humans})


