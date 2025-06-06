# Place2BE opstartgids
## Stap 1) download IntelliJ & add java 
## Stap 2) Java versie 17 downloaden: https://www.azul.com/downloads/?version=java-17-lts&package=jdk#zulu (MSI)
	Bij de installatie zorg je dat alle opties aangevinkt zijn 
	check in je terminal of java werkt (java -version), zo niet, herstart alles 
## Stap 3) download de repo lokaal
	git init
	git clone repo
## Stap 3) docker installatie 
	zorg dat je de dump.sql file hebt 
	via powershell => ga naar de locatie waar je dump file zich bevind => Get-Content .\dump.sql | Set-Content -Encoding UTF8 .\dump-utf8.sql
	ga in je project naar de file waar het docker bestand zich bevindt, doe dan: 
	docker cp dump-utf8.sql freespots_db:/dump-utf8.sql
	docker exec -it freespots_db psql -U root -d freespots_db -f /dump-utf8.sql
## Stap 4) mvn (maven) installeren met mvn -install
## Stap 5) backend opzetten
	Zorg dat je in je backend folder van je project zit: <pad naar locale repo>\PlaceToBE\Backend\generic-free-spots-backend. doe dan: 
	mvn spring-boot:run, of als de poort 8080 al in gebruik is: mvn spring-boot:run "-Dspring-boot.run.arguments=--server.port=8081"
## Stap 6) frontend opzetten:
	ga in de gewonde cmd naar <pad naar locale repo>\PlaceToBE\Frontend\gvpp
	geef npm run dev in
	ga naar http://localhost:5173/
