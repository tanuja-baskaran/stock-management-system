from flask import Flask, render_template
import requests

app = Flask(__name__)

@app.route('/')
def home():
    response = requests.get('http://localhost:8080/inventory')  # Adjust port as needed
    inventory = response.json()
    return render_template('index.html', inventory=inventory)

if __name__ == '__main__':
    app.run(debug=True)
