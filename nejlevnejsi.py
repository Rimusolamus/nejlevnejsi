from flask import Flask
from flask_pymongo import MongoClient, request
import json
from bson import json_util, ObjectId


app = Flask(__name__)
client = MongoClient('mongodb://0.0.0.0:27017/nejlevnejsi')
db = client.nejlevnejsi


@app.route('/getTags')
def getTags():
    return json.loads(json_util.dumps({"tags":[tag for tag in db.tags.find()]}))


@app.route('/index')
@app.route('/')
def index():
    return "sup rohlik"


@app.route('/getTopOffers')
def getTopOffers():
    return json.loads(json_util.dumps({"offers":[offer for offer in db.offers.find()]}))


@app.route('/addOffer')
def addOffer():
    db.offers.insert_one(request.get_json())


if __name__ == '__main__':
    # Threaded option to enable multiple instances for multiple user access support
    app.run(threaded=True, host='0.0.0.0', debug=True)
