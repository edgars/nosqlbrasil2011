
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>NoSQLBrasil: Trends</title>
	<link rel="stylesheet"  href="http://code.jquery.com/mobile/1.0b2/jquery.mobile-1.0b2.min.css" />
	<script src="http://code.jquery.com/jquery-1.6.2.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.0b2/jquery.mobile-1.0b2.min.js"></script>
	
</head> 
<body>

 
<div data-role="page" id="jqm-home" class="type-home">


	<div data-role="content">
		
		
		<div class="content-secondary">
	
			<div id="jqm-homeheader">
				<h1 id="jqm-logo"><img src="nosqlbrv2_logo_q.png" alt="NoSQL:Brasil" /></h1>
				<h1>Infinispan Demo App</h1>
				<p id="jqm-version">nosql:brasil 2011 : http://nosqlbr.com/</p>
			</div>
	
	

		</div><!--/content-primary-->	
		
		<div class="content-primary">
		
		<div data-role="collapsible" data-collapsed="true" data-theme="a">
        <h3>Comment a Session</h3>

	    <div data-role="fieldcontain">
	    

	    <label for="select-choice-1" class="select">Select Session:</label>
     	<select name="select-choice-1" id="select-choice-1">
		<option value="standard">Standard: 7 day</option>
		<option value="rush">Rush: 3 days</option>
		<option value="express">Express: next day</option>
		<option value="overnight">Overnight</option>
	    </select>


	<label for="textarea">Your Comment (Be Polite):</label>
	<textarea cols="40" rows="8" name="textarea" id="textarea" maxlength="170"></textarea>
	
	<div data-role="fieldcontain">
    <label for="name">Twitter Account:</label>
    <input type="email" name="name" id="name" value=""  />
</div>	

         </div>	

		</div>
		
			<nav>
				
		
				<ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
					<li data-role="list-divider">Last Comments</li>
					<li><a href="docs/pages/index.html">Pages &amp; dialogs</a></li>
					<li><a href="docs/toolbars/index.html">Toolbars</a></li>
					<li><a href="docs/buttons/index.html">Buttons</a></li>
					<li><a href="docs/content/index.html">Content formatting</a></li>
					<li><a href="docs/forms/index.html">Form elements</a></li>
					<li><a href="docs/lists/index.html">List views</a></li>
				</ul>
			</nav>
		</div>

		

	</div>
	
	<div data-role="footer" class="footer-docs" data-theme="c">
			<p>&copy; Edgar Silva - Red Hat do Brasil</p>
	</div>	
	
</div>
</body>
</html>
