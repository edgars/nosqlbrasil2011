package br.com.redhat.infinispan.sample.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;



import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.infinispan.Cache;
import org.infinispan.config.Configuration;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

import br.com.redhat.infinispan.nosqlbrasil.Session;



@Path("/rest")
public class Command {
	
    Cache<String, Object> cache ;
    Cache<Object, String> cacheSessions ;	
    
   @javax.ws.rs.core.Context HttpServletRequest request;
     
    public Command() {
    	
    	cache = new DefaultCacheManager().getCache();
    	
    	
    	EmbeddedCacheManager manager = new DefaultCacheManager();
    	         manager.
    	         defineConfiguration("comments-cache", 
    			 new Configuration().fluent()
    			.eviction().strategy(EvictionStrategy.LIRS).maxEntries(20)
    			.build());
    	         
    	 cacheSessions = manager.getCache("comments-cache");        
    	
    	Session s = new Session("L'esprit de l'escalier","Gleicon Moraes e John D. Rowell");
    	cache.put(s.getName(), s);
    	
    	s = new Session("Modelling with Graphs","Alistair Jones");
    	cache.put(s.getName(), s);
    	
    	s = new Session("OpenShift: NoSQL a la carte num PaaS 100% Open Source","Edgar Silva ");
    	cache.put(s.getName(), s);
    	
    	s = new Session("Data Fabric: performance e escalabilidade em grid","Frederico Melo");
    	cache.put(s.getName(), s);
    	
    	s = new Session("Prevayler - Você Ainda Usa Banco de Dados?","Klaus Wuestefeld");
    	cache.put(s.getName(), s);
    	
    	s = new Session("Apache Cassandra e Brisk","Benjamin Coverston");
    	cache.put(s.getName(), s);
    	
    	s = new Session("Modelos ricos: outro motivo para usar NoSQL","Luciano Ramalho");
    	cache.put(s.getName(), s);

    	s = new Session("Real-time web analytics com MongoDB","Victor Augusto");
    	cache.put(s.getName(), s);
    	
    	s = new Session("Benchmark LucidDB x MySQL em aplicações de BI","Fernando Masanori Ashikaga");
    	cache.put(s.getName(), s);

 
    	
 	}
    
    @GET
    @Path("home")
    @Produces("text/html; charset=UTF-8")
	public String index() {

		
		HashMap<String,Object> vars = new HashMap<String,Object>();
		
		List<Session> sessions = new ArrayList<Session>();
		for (Entry<String, Object> entry: cache.entrySet()) {
			
			sessions.add((Session) entry.getValue());
		}
		
		vars.put("sessions", sessions);
		vars.put("cache", cache);
		    

		
		String html = getTemplate("list.vm",   vars);
		
		return html;
	}
  
 
	@POST
    @Path("add")
    @Produces("text/html; charset=UTF-8")
	public String addComment(@Context HttpServletRequest request) {

		
		HashMap<String,Object> vars = new HashMap<String,Object>();

		Session s = (Session) cache.get(request.getParameter("sessionName"));
		
		List<String> comments = new ArrayList<String>();
		
		comments.add(request.getParameter("comment"));
		
		s.setComments(comments);
		
		cache.put(request.getParameter("sessionName"), s);
		
		cacheSessions.put(s, request.getParameter("comment"));
		
		comments.clear();
		
		for (Entry<Object, String> entry: cacheSessions.entrySet()) {
			
			comments.add((String) entry.getValue() + " made in: " + ((Session)entry.getKey()).getName());
		}
		
		List<Session> sessions = new ArrayList<Session>();
		for (Entry<String, Object> entry: cache.entrySet()) {
			
			sessions.add((Session) entry.getValue());
		}
		
		vars.put("sessions", sessions);
		
		vars.put("comments", comments );
		
		String html = getTemplate("list.vm",   vars);
		
		return html;
	}
	
	
	@GET
    @Path("viagem/{trip}/pdf")
    @Produces("application/pdf; charset=UTF-8")
	public byte[] returnPDF(@PathParam("trip") String trip) {
		
		byte[] bytes = null ;
        
		try {
			File file = new File("/home/esilva/esb/mail/" + trip + "_form.pdf");
			InputStream is = new FileInputStream(file);
			long length = file.length();
			bytes = new byte[(int) length];
			// Read in the bytes
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length
					&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
			// Ensure all the bytes have been read in
			if (offset < bytes.length) {
				throw new IOException("Could not completely read file "
						+ file.getName());
			}
			// Close the input stream and return bytes
			is.close();
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return bytes;

	}
	
	
	
	@GET
    @Path("viagem/{trip}/detalhes")
    @Produces("text/html; charset=UTF-8")
	public String detailTrip(@PathParam("trip") String trip) {
		
	

		
		HashMap<String,Object> vars = new HashMap<String,Object>();

		
		String html = getTemplate("DetailTrip.vm",   vars);
		
		return html;
	}
	
    
    @GET
    @Path("/home")
    @Produces("text/html")
    public String home(){
            
            List<java.lang.reflect.Method> methods = Arrays.asList(this.getClass().getDeclaredMethods());
            
            StringBuilder b = new StringBuilder();
            
            b.append("<html><title>Red Hat LATAM - TripBooker</title><head><style>.x{font-family: verdana,ari; color: steelblue; font-size: small;;}" +
                            "</style></head><body><font face='Verdana' color=''><h3>TripBooker</h3>" +
                            "<hr size=1><p> List of Actions:<ul>");
            
    for (java.lang.reflect.Method m : methods) {
            
            if (null != m.getAnnotation(Path.class)) {
                    
                    Path p = m.getAnnotation(Path.class);
                    
                    b.append(  String.format("<li class='x'><b>%s</b> with annotation: <a href='jbpm%s'><u>%s</u> </a> </li>\n", m.getName(), p.value() ,p.value()) );
                    
            }
                    
            }
    b.append("</ul><hr size=1><small>Flowlet - Is member of Breakingwoods Project</small></a></body></html>");
            
            return b.toString();
    }

	
	
	
	
	private String getTemplate(String templateFile , Map<String, Object> vars) {
		
		 VelocityEngine ve = new VelocityEngine();
		 Properties p = new Properties();
		 p.put("file.resource.loader.path",request.getSession().getServletContext().getRealPath("/templates"));
		 p.put("file.resource.loader.cache","true");
		 p.put("input.encoding","UTF-8");
		 p.put("output.encoding","UTF-8");

		 
		 ve.init(p);
		 
		 vars.put("app",   request.getServerName() + ":" + request.getLocalPort() + request.getContextPath());

	
		 
		VelocityContext context = new VelocityContext();
		
		for (String key : vars.keySet()) {
			
			context.put(key, vars.get(key));
		}
		
		
		
	     Template template =  null;

         try
         {
        	 template = ve.getTemplate( templateFile , "UTF-8");
         }
         catch( ResourceNotFoundException rnfe )
         {
             System.out.println("Example : error : cannot find template " + templateFile );
             rnfe.printStackTrace();
         }
         catch( ParseErrorException pee )
         {
             System.out.println("Example : Syntax error in template " + templateFile + ":" + pee );
         }
	    
         StringWriter  writer = new StringWriter( );

             if ( template != null)
                 
            	 template.merge(context, writer);

			return writer.toString();
		
	}
	
}
