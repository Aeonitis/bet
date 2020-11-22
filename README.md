# bet
Work Done:
- Calculate liability
- JSON/CSV API input
- Actuator added (DevOPs ready)
- Service output reports to console(config based out variable)
- Error handling
- Serialize/Convert both formats
- Logger
- Input Validation/NonNull params
- Integration tests
- Unit tests (not enough)


To upload a csv file [source.csv](https://github.com/Aeonitis/bet/blob/main/moneybags/src/main/resources/static/source.csv) via curl POST:
```sh
curl -F file=@"source.csv" http://localhost:8080/post-csv
```

To POST a JSON file [source.json](https://github.com/Aeonitis/bet/blob/main/moneybags/src/main/resources/static/source.json) as a request:
```sh
curl -vX POST http://localhost:8080/post-json -d @source.json --header "Content-Type: application/json"
```

Output Reports/Resulting CSVs:
- [ReportOne](https://github.com/Aeonitis/bet/blob/main/moneybags/ReportOne.csv)
- [ReportTwo](https://github.com/Aeonitis/bet/blob/main/moneybags/ReportTwo.csv)

Todos, Extras to consider in future:
- More Exception handling
- More Unit tests
- More Field/Constraint Validators

Questions on requirements:
- Format, single endpoint for both csv/json as string data or both multi-part files curl object-mapping required? 
- Max post/upload request size?

What's not working to my liking (non-requirements):
- CurrencySymbol in console, ISO encoding 
- Csv with or without header as optional
- TextTable toCsv() method may work better
- TODOs in code

Source:
```sh
betId, betTimestamp, selectionId, selectionName, stake, price, currency
Bet-10, 1489490156000, 1, My Fair Lady, , 6.0, GBP
Bet-11, 1489490156000, 2, Always a Runner, 1.25, 4.0, EUR
Bet-12, 1489230956000, 4, Bilbo's Adventure, 5.0, 4.5, GBP
Bet-13, 1489403756000, 3, Fan the Flames, 4.5, 5.5, GBP
Bet-14, 1489144556000, 2, Always a Runner, 7.9, 7.0, EUR
Bet-15, 1489140956000, 1, My Fair Lady, 3.4, 6.5, EUR
Bet-16, 1489227356000, 4, Bilbo's Adventure, 2.5, 6.5, GBP
Bet-17, 1489313756000, 2, Always a Runner, 1.5, 11.0, EUR
Bet-18, 1489310156000, 2, Always a Runner, 3.8, 5.5, GBP
Bet-19, 1489482956000, 3, Fan the Flames, 3.4, 4.0, GBP
Bet-20, 1489396556000, 4, Bilbo's Adventure, 2.25, 5.0, EUR
Bet-21, 1489137356000, 2, Always a Runner, 5.4, 6.5, EUR
Bet-22, 1489223756000, 3, Fan the Flames, 6.7, 6.5, GBP
Bet-23, 1489310156000, 3, Fan the Flames, 1.1, 4.5, EUR
Bet-24, 1489324556000, 4, Bilbo's Adventure, 2.0, 6.5, GBP
Bet-25, 1489151756000, 2, Always a Runner, 3.2, 6.0, GBP
Bet-26, 1489497356000, 2, Always a Runner, 4.2, 5.0, EUR
Bet-27, 1489410956000, 3, Fan the Flames, 2.1, 4.5, EUR
Bet-28, 1489324556000, 1, My Fair Lady, 7.8, 5.5, GBP
Bet-29, 1489320956000, 4, Bilbo's Adventure, 6.2, 6.5, GBP
Bet-30, 1489493756000, 4, Bilbo's Adventure, 8.4, 7.5, EUR
Bet-31, 1489407356000, 1, My Fair Lady, 10.5, 7.3, GBP
Bet-32, 1489320956000, 3, Fan the Flames, 5, 5.5, GBP
Bet-33, 1489234556000, 2, Always a Runner, 0.75, 7.0, EUR
```