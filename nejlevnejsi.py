from flask import Flask
from flask_pymongo import MongoClient, request
import json
from bson import ObjectId


class JSONEncoder(json.JSONEncoder):
    def default(self, o):
        if isinstance(o, ObjectId):
            return str(o)
        return json.JSONEncoder.default(self, o)


app = Flask(__name__)
client = MongoClient('mongodb://localhost:27017/nejlevnejsi')
db = client.nejlevnejsi


@app.route('/getTags')
def getTags():
    return JSONEncoder().encode(db.tags.find_one_or_404())


@app.route('/')
def index():
    return "it works"


@app.route('/getTopOffers')
def getTopOffers():
    return JSONEncoder().encode(db.offers.find())


@app.route('/addOffer')
def addOffer():
    db.offers.insert_one(request.get_json())


if __name__ == '__main__':
    # Threaded option to enable multiple instances for multiple user access support
    app.run(threaded=True, host='0.0.0.0')
