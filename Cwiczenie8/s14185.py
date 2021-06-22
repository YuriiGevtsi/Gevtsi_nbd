#!/usr/bin/env python.
# -*- coding: utf-8 -*-
import riak

def addDocument(bucket, key, data):
    newDocument = bucket.new(key, data)
    newDocument.store()
    if bucket.get(key).exists:
        print("Document added: " + key)
    else:
        print("Error " + key)
        assert bucket.get(key).exists == True

def fetchDocument(bucket, key):
    return bucket.get(key)

def fetchAndPrintDocument(bucket, key):
    document = fetchDocument(bucket, key)
    if document.exists:
        print("Document exist: %s - %s" % (key, document.encoded_data))
    else:
        print("Not found: %s (%s)" % (key, document.encoded_data))
        assert document.exists == True

def updateDocument(bucket, key, item, newValue):
    document = fetchDocument(bucket, key)
    document.data[item] = newValue
    document.store()
    if document.data[item] == newValue:
        print("Document: %s - updated (edited filed '%s' new val %s)" % (key, item, str(newValue)))
    else:
        print("Document: %s - failed (field '%s' : %s)" % (key, item, document.data[item]))
        assert document.data[item] == newValue

def deleteDocument(bucket, key):
    document = fetchDocument(bucket, key)
    document.delete()
    if document.data == None:
        print("Document: %s - deleted" % key)
    else:
        print("Document: %s - failed" % key)
        assert document.data == None

if __name__ == "__main__":
    # Python client uses the Protocol Buffers interface ('pbc') by default
    myClient = riak.RiakClient(port = 8098)

    # bucket
    myBucket = myClient.bucket('students')

    # data
    student = {
        'number': 14185,
        'name': "Yurii Gevtsi",
        'Year': 22
    }

    # Wrzuca do bazy Document
    addDocument(myBucket, str(student['number']), student)

    # Pobiera i wypisuje Document
    fetchAndPrintDocument(myBucket, str(student['number']))

    # Modyfikuje Document
    updateDocument(myBucket, str(student['number']), "number", 141850)

    # Pobiera i wypisuje Document
    fetchAndPrintDocument(myBucket, str(student['number']))

    # Usuwa Document
    deleteDocument(myBucket, str(student['number']))

    # Pobiera i wypisuje Document
    fetchAndPrintDocument(myBucket, str(student['number']))
