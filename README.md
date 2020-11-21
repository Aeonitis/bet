# bet
- Calculate liability
- JSON/CSV API input
- Actuator (DevOPs ready)
- Service output reports to console(config based out variable)
- Unit tests
- Error handling
- Serialize/Convert both formats
- Input Validation/NonNull params
- Logger


To upload a file via curl POST:
```sh
curl -F file=@"source.csv" http://localhost:8080/post-csv
```