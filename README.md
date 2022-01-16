# Check URL

Check if your urls are working in a a single step!

## How the magic happens

You create file with a list of URLs and this program will check them for you.

## How to use it

You have to create a [CSV file](https://en.wikipedia.org/wiki/Comma-separated_values) with a URLs for line in one of theese following two formats:

### Simple site

For a simple site check like `www.mysite.com` you should add at least an simple id so you can organize the results.

Ex.: `1;192.168.16.52;`

Esse será convertido em http://192.168.16.52:8080 e testado usando HTTP e telnet e o resultado será no formato

```log
1;http://192.168.16.52:8080;false;false
```

No final os dois booleanos são o resultado da conexão HTTP e telnet respectivamente

Funciona com hosts que possuam nome

```log
1;google.com;80  --> 1;http://google.com:80;true;true
```

### ID;IP;PORT_RANGE_BEGIN:PORT_RANGE_END

Ex: `1;google.com;80:86`

O programa irá ler os números 80:86 como um range e testar cada um deles.
