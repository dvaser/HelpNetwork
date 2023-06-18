from django.shortcuts import render
import requests

# Create your views here.
def menu(request):
    if request.method == 'POST':
        print(request.POST) 
        fromCity = request.POST.get('fromCity')
        type = request.POST.get('type')
        material = request.POST.get('material')
        adet = request.POST.get('adet')

        print(fromCity)
        
        json = {
            'from':fromCity,
            'name':'',
            'productName':material,
            'number': adet,    
            'isHuman': type=='insan',
            'phoneNumber': ''
        }

        url = "http://localhost:8080/v1/assistance/create"
        res = requests.post(url, json=json)
        
    url = 'http://localhost:8080/v1/assistance/assistances'
    response = requests.get(url)

    data = {}
    if response.status_code == 200:
        data = response.json()
        print(data)
    else:
        pass
    return render(request, 'belediye/belediyeMenu.html', {"data":data})