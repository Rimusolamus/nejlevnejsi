from flask import Flask
app = Flask(__name__)

@app.route('/getTags')
def getTags():
    return {"tags":["smazak","burger","kridla","polevka","vegan"]}

@app.route('/')
def index():
    return "it works"

@app.route('/getTopOffers')
def getTopOffers():
    return {"offers":[{"name":"text1", "description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."},{"name":"text2", "description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."},{"name":"text3", "description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."}]}

if __name__ == '__main__':
    # Threaded option to enable multiple instances for multiple user access support
    app.run(threaded=True, host='0.0.0.0')
