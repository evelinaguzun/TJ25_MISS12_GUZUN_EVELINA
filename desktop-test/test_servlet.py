import requests


url = "http://localhost:8080/temalab1/controller"
params = {"page": "2"}

headers = {
    "User-Agent": "PythonDesktopClient/1.0"
}
response = requests.get(url, params=params, headers=headers)

print("Response status:", response.status_code)
print("Response body:", response.text)
