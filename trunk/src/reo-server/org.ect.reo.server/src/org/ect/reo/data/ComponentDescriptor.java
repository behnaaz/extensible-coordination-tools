package org.ect.reo.data;

import org.ect.reo.Component;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.actions.PageHelper;
import org.ect.reo.util.PropertyHelper;
import org.ect.reo.util.ReoNames;

/**
 * Component descriptor class. Used for code generation.
 */
public class ComponentDescriptor {
	
	public String componentName;
	public String packageName;
	public String[] sources;
	public String[] sinks;
	public Component component;
	public String sourcesServiceURL;
	public String sinksServiceURL;
	
	
	public ComponentDescriptor(Component component, PageHelper helper) {
		
		this.component = component;
		Workspace workspace = helper.getWorkspace();
		
		componentName = componentName(component, workspace.getIdentifier(component));
		packageName = packageName(component);
		sources = new String[component.getSourceEnds().size()];
		sinks = new String[component.getSinkEnds().size()];
		
		for (int i=0; i<sources.length; i++) {
			SourceEnd end = component.getSourceEnds().get(i);
			sources[i] = workspace.getIdentifier(end).toString();
		}
		for (int i=0; i<sinks.length; i++) {
			SinkEnd end = component.getSinkEnds().get(i);
			sinks[i] = workspace.getIdentifier(end).toString();
		}
		
		sinksServiceURL = helper.getServletContext().getInitParameter("sinks");
		sourcesServiceURL = helper.getServletContext().getInitParameter("sources");
		
	}
	
    private String componentName(Component component, NetworkIdentifier identifier) {
		String name = normalize(ReoNames.getName(component));
		if (name==null || name.trim().equals("")) {
			name = "Component";
		}
		return normalize(name + identifier);
	}
    
    private String packageName(Component component) {
		String name = normalize(PropertyHelper.getFirstValue(component, "package"));
		if (name==null || name.trim().equals("")) {
			name = "components";
		}
		return name;
	}
    
    private String normalize(String name) {
    	if (name==null) return null;
    	name = name.replaceAll(" ", "");
    	name = name.replaceAll(":", "");
    	name = name.replaceAll("\n", "");
    	name = name.replaceAll("-", "_");
    	return name;
    }
    
}