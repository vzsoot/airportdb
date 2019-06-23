<!DOCTYPE HTML>
<html lang="en">
<head>
    <title>Airportdb Range Search</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <style>
        .error {
            color: rgba(255, 0, 0, 0.5)
        }
    </style>
</head>
<body>
<form method="post" action="/search">
    <h3>Search for nearby airports:</h3>
    <table>
        <#if error??>
        <tr><td class="error" colspan="2">${error}</td></tr>
        </#if>
        <tr>
            <td><label for="latitude">Your latitude (°)</label></td>
            <td><input id="latitude" type="text" name="latitude" value="${(query.latitude)!""}" /></td>
        </tr>
        <tr>
            <td><label for="longitude">Your longitude (°)</label></td>
            <td><input id="longitude" type="text" name="longitude" value="${(query.longitude)!""}" /></td>
        </tr>
        <tr>
            <td><label for="radius">Radius (km)</label></td>
            <td><input id="radius" type="text" name="radius" value="${(query.radius)!""}" /></td>
        </tr>
        <tr><td colspan="2"><button type="submit">Search</button></td></tr>
    </table>

    <#if airports??>
    <table>
        <thead>
            <tr><th>Country</th><th>City</th><th>Airport</th><th>Distance (km)</th></tr>
        </thead>
        <#list airports as airport>
            <tr>
                <td>
                    ${airport.airport.country}
                </td>
                <td>
                    ${airport.airport.city}
                </td>
                <td>
                    ${airport.airport.name} (${airport.airport.icao})
                </td>
                <td>
                    ${airport.distance}
                </td>
            </tr>
        </#list>
    </table>
    </#if>
</form>
</body>
</html>