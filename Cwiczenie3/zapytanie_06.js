//6. Dodaj siebie do bazy, zgodnie z formatem danych użytych dla innych osób (dane dotyczące karty kredytowej, adresu zamieszkania i wagi mogą być fikcyjne);
printjson(db.people.insertOne(
  {
		"sex" : "Male",
		"first_name" : "first_name",
		"last_name" : "last_name",
		"job" : "job",
		"email" : "email",
		"location" : {
			"city" : "Warsaw",
			"address" : {
				"streetname" : "streetname",
				"streetnumber" : "1"
			}
		},
		"description" : "description",
		"height" : "200",
		"weight" : "100",
		"birth_date" : "1998-01-08T00:00:00Z",
		"nationality" : "nationality",
		"credit" : [
			{
				"type" : "type",
				"number" : "1234876501236789",
				"currency" : "currency",
				"balance" : "00.00"
			}
		]
	}
))
