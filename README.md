# Thuusbezorgd

## Inleiding

Welkom bij de Thuusbezorgd Opdracht. In deze opdracht gaan jullie een basic applicatie in stukken verdelen zodat verschillende
teams van developers hier onafhankelijk aan door kunnen ontwikkelen.

Deze repository bestaat uit 3 branches:

1: monoliet-geen-security: Hier staat een more-or-less werkende applicatie als 1 geheel. Dat is het ding dat je in stukken moet
hakken
2: leeg (U BENT NU HIER): Hier staat een bijna-lege repository met wat Maven en Docker gefrutsel klaar
3: monoliet: hier is ook Spring Security gebruikt. Dat levert zo veel meer technisch geneuzel op dat het optioneel
, maar wel interessant kan zijn om mee te nemen.

Het is redelijk goed te doen om met deze opdracht ook de SQL doelen van eerder te behalen. Dus als je de SQL opdracht hebt
gedaan, gebruik bijv. MongoDB en Neo4J als databases, en als je juist de NoSQL opdracht hebt gedaan dan kun je wat extra
liefde in de PostGres mapping stoppen.

Je mag helemaal zelf weten wat je prettig vindt werken natuurlijk, maar als ik zelf deze opdracht zou maken, dan zou ik
2 checkouts van de code naast elkaar zetten. Ik zou in de ene op de 'leeg' branch gaan zitten, en in de andere op een van
de monoliet branches (start zonder security zou mijn tip zijn). Vervolgens zou ik langzaam code migreren van de ene naar de andere.

Het (ook goede) alternatief is om juist een branch te gaan herschrijven. Dat zou in een professionele context het voordeel
hebben dat je een beter versie-beheer log hebt van hoe de transformatie is gebeurd. Ik zou het persoonljk lastiger vinden
om te doen.

## Opstarten

Tsja, deze repository is leeg. En doet dus nog niet zoveel:)

Het is handig om even naar de verschillende pommetjes te kijken om te zien hoe de fork in de steel zit. 
Er is 1 Parent pom, in de hoofd directory, en die linkt de andere modules aan elkaar.

Elk van de andere pommetjes heeft ook common als dependency, dus daar kun je evt. gedeelde code in kwijt. Maar let op,
hoe meer code je deelt tussen je services, hoe meer koppeling! Er zijn zat bedrijven die zo'n gedeelde library echt een
no-go vinden, dus bedenk goed wat je daar wel/niet in zet.
