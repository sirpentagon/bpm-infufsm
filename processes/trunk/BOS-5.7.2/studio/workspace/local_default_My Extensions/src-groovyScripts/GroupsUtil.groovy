/*METADATAList<String> getAllTipoACG()=No Documentation
boolean requerTutor(String tipo)=No Documentation
HashMap<String,String> buildChildrenGroupsPathMAP(List<Group> groupsToFilter, String parentGroupUUID)=No Documentation
ArrayList usuariosDoGrupo(List<String> path)=No Documentation
HashMap<String,String> buildGroupsMAP(List<Group> groups)=No Documentation
HashMap<String,String> getPathOfChildren(String parentGroupUUID)=No Documentation
String buildPath(Group g)=No Documentation
List<Group> getAllGroups()=No Documentation
*//*METADATAString getPathGroupUUID(List<String> path)=No Documentation
HashMap<String,String> buildChildrenGroupsPathMAP(List<Group> groupsToFilter, String parentGroupUUID)=No Documentation
ArrayList usuariosDoGrupo(List<String> path)=No Documentation
HashMap<String,String> buildGroupsMAP(List<Group> groups)=No Documentation
HashMap<String,String> getPathOfChildren(String parentGroupUUID)=No Documentation
String buildPath(Group g)=No Documentation
List<Group> getAllGroups()=No Documentation
*/
import org.ow2.bonita.facade.identity.Group;
import org.ow2.bonita.facade.APIAccessor;
import org.ow2.bonita.facade.impl.StandardAPIAccessorImpl;
import org.ow2.bonita.facade.exception.GroupAlreadyExistsException;
 import org.ow2.bonita.facade.exception.GroupNotFoundException;
 import org.ow2.bonita.facade.exception.MembershipNotFoundException;
 import org.ow2.bonita.facade.exception.MetadataAlreadyExistsException;
 import org.ow2.bonita.facade.exception.MetadataNotFoundException;
 import org.ow2.bonita.facade.exception.RoleAlreadyExistsException;
 import org.ow2.bonita.facade.exception.RoleNotFoundException;
 import org.ow2.bonita.facade.exception.UserAlreadyExistsException;
 import org.ow2.bonita.facade.exception.UserNotFoundException;
 import org.ow2.bonita.facade.identity.Group;
 import org.ow2.bonita.facade.identity.Membership;
 import org.ow2.bonita.facade.identity.ProfileMetadata;
 import org.ow2.bonita.facade.identity.Role;
 import org.ow2.bonita.facade.identity.User;
 import org.ow2.bonita.util.AccessorUtil;
 import org.ow2.bonita.facade.identity.User;
 import org.ow2.bonita.facade.ManagementAPI;
 import org.ow2.bonita.facade.QueryRuntimeAPI;
 import java.io.File;
 import java.io.FileOutputStream;
 import org.ow2.bonita.connector.core.ConnectorError;
 import org.ow2.bonita.connector.core.ProcessConnector;
 import org.ow2.bonita.facade.runtime.AttachmentInstance;
 import org.ow2.bonita.util.AccessorUtil;
 import java.io.File;
 import java.io.FileOutputStream;
 import org.ow2.bonita.facade.runtime.AttachmentInstance;
 import org.ow2.bonita.util.AccessorUtil;
 import org.ow2.bonita.facade.uuid.ActivityInstanceUUID;
 import org.ow2.bonita.facade.uuid.CatchingEventUUID;
 import org.ow2.bonita.facade.uuid.DocumentUUID;
 import org.ow2.bonita.facade.uuid.ProcessDefinitionUUID;
 import org.ow2.bonita.facade.uuid.ProcessInstanceUUID;
 import org.ow2.bonita.light.LightActivityInstance;
 import org.ow2.bonita.light.LightProcessInstance;
 import org.ow2.bonita.light.LightTaskInstance;
 import org.ow2.bonita.search.DocumentResult;
import org.ow2.bonita.search.DocumentSearchBuilder;
import org.ow2.bonita.search.SearchQueryBuilder;
 import org.w3c.dom.Node;
 import org.ow2.bonita.facade.runtime.Document;
 import org.ow2.bonita.search.DocumentSearchBuilder;
 import org.ow2.bonita.search.DocumentResult;
 import org.ow2.bonita.search.index.DocumentIndex;
 import org.ow2.bonita.search.DocumentCriterion;
 import org.ow2.bonita.search.DocumentSearchBuilder;
 
 import org.ow2.bonita.search.DocumentResult;
 import org.ow2.bonita.search.index.DocumentIndex;
 
 import org.ow2.bonita.facade.APIAccessor;
 import org.ow2.bonita.facade.BonitaApplicationAccessContext;
 import org.ow2.bonita.facade.QueryRuntimeAPI;
 import org.ow2.bonita.facade.impl.StandardAPIAccessorImpl;
 import org.ow2.bonita.facade.runtime.ProcessInstance;
 import org.ow2.bonita.facade.uuid.DocumentUUID;
 import org.ow2.bonita.services.Document;
 import java.net.FileNameMap;
 import java.net.URLConnection;
 import javax.activation.MimetypesFileTypeMap;
 import java.io.File;
 import java.io.*;
 import java.util.zip.*;
 import java.util.*;
 import java.text.*;

 
public class GroupsUtil {
	
	/*static String attachVariable;
	static String pathToOutPut;
	static String attFileExtension;*/

/*
(tipoACG=="II - Atuacao em nucleos tematicos;" || tipoACG=="III - Atividades de extensao" || tipoACG=="IV - Estagio extra-curricular" || tipoACG=="V - Atividade de iniciacao cientifica;" || tipoACG=="VIII - Monitoria;")&&nomeTutor==tutorNaoCadastrado


tipoACG!="II - Atuacao em nucleos tematicos;" && tipoACG!="III - Atividades de extensao" && tipoACG!="IV - Estagio extra-curricular" && tipoACG!="V - Atividade de iniciacao cientifica;" && tipoACG!="VIII - Monitoria;"
*/

static int ind = 0;

static String[] tipoACG = ["I - Participacao em eventos;",
	"II - Atuacao em nucleos tematicos;",
	"III - Atividades de extensao",
	"IV - Estagio extra-curricular",
	"V - Atividade de iniciacao cientifica;",
	"VI - Publicacao de trabalho;",
	"VII - Participacao em orgaos colegiados;",
	"VIII - Monitoria;",
	"IX - Outra atividade a criterio do Colegiado."]

public static List<String> getAllTipoACG() {
	return java.util.Arrays.asList(tipoACG);
}

public static boolean requerTutor(String tipo) {
	return tipo.equals(tipoACG[1]) ||
		tipo.equals(tipoACG[2]) ||
		tipo.equals(tipoACG[3]) ||
		tipo.equals(tipoACG[4]) ||
		tipo.equals(tipoACG[7]);
}

// Participacao em eventos tem tramite curto
public static boolean tramiteCurto(String tipo) {
	return tipo.equals(tipoACG[0]);
}

public static String buildPath(Group g) {
	final StringBuilder builder = new StringBuilder();
	Group currentGroup = g;
	while (currentGroup != null) {
		builder.insert(0, currentGroup.getName());
		builder.insert(0, "/");
		Group parent = currentGroup.getParentGroup();
		if (parent != null) {
			currentGroup = parent;
		} else {
			currentGroup = null;
		}
	}
	return builder.toString();
}

public static HashMap<String,String> getPathOfChildren(String parentGroupUUID) {
	final APIAccessor accessor = new StandardAPIAccessorImpl();
	final Collection<Group> groups = accessor.getIdentityAPI().getChildrenGroupsByUUID(parentGroupUUID);
	final HashMap<String,String> result = new HashMap<String,String>();
	String label;
	for(Group g: groups) {
		label = (g.getName() + " (" + buildPath(g)+ ")");
		result.put label, buildPath(g);
	}
	return result;
}

public static HashMap<String,String> buildChildrenGroupsPathMAP(List<Group> groupsToFilter, String parentGroupUUID) {
	final HashMap<String,String> result = new HashMap<String,String>();
	if(parentGroupUUID==null || groupsToFilter == null || groupsToFilter.isEmpty()) {
		return result;
	}
	String label;
	for(Group g: groupsToFilter) {
		if(g.getParentGroup()!=null && parentGroupUUID.equals(g.getParentGroup().getUUID())) {
			label = (g.getName() + " (" + buildPath(g)+ ")");
			result.put(label, buildPath(g));
		}
	}
	return result;
}

public static HashMap<String,String> buildGroupsMAP(List<Group> groups) {
	final HashMap<String,String> result = new HashMap<String,String>();
	String label;
	for (Group g : groups) {
		label = (g.getName() + " (" + buildPath(g)+ ")");
		result.put(label ,g.getUUID());
	}
	
	return result;
}

public static List<Group> getAllGroups() {
	final APIAccessor accessor = new StandardAPIAccessorImpl();
	return accessor.getIdentityAPI().getAllGroups();
}

public static String getPathGroupUUID(List<String> path) {
	final APIAccessor accessor = new StandardAPIAccessorImpl();
	Group g = accessor.getIdentityAPI().getGroupUsingPath(path);
	return g.getUUID();
}


public static ArrayList usuariosDoGrupo(List<String> path){//recebe uma list de string com o caminho do grupo
	String caminho;
	Group g = AccessorUtil.getIdentityAPI().getGroupUsingPath(path);
	caminho=g.getUUID();
	List<User> users = AccessorUtil.getIdentityAPI().getAllUsersInGroup(caminho);
	List userName = new ArrayList();
	for(int i=0;i<users.size();i++){
		userName.add(users.get(i).username);
	}
	return userName;
}

//recebe uma list de string com o caminho do grupo e uma variavel com nome a incluir
public static ArrayList usuariosDoGrupoIncludingOne(List<String> path, String include) {

	String caminho;
	Group g = AccessorUtil.getIdentityAPI().getGroupUsingPath(path);
	caminho=g.getUUID();
	List<User> users = AccessorUtil.getIdentityAPI().getAllUsersInGroup(caminho);
	List userName = new ArrayList();
	for (User u : users) {
		userName.add(u.username);
	}
	userName.add(include);
	return userName;
}

//recebe uma list de string com o caminho do grupo e uma variavel com nome a excluir
public static ArrayList usuariosDoGrupoExcludingOne(List<String> path, String exclude) {

	String caminho;
	Group g = AccessorUtil.getIdentityAPI().getGroupUsingPath(path);
	caminho=g.getUUID();
	List<User> users = AccessorUtil.getIdentityAPI().getAllUsersInGroup(caminho);
	List userName = new ArrayList();
	for (User u : users) {
		userName.add(u.username);
	}
	userName.remove(exclude);
	return userName;
}

public static int numMembros(List<String> path){
	String caminho;
	Group g = AccessorUtil.getIdentityAPI().getGroupUsingPath(path);
	caminho=g.getUUID();
	List<User> users = AccessorUtil.getIdentityAPI().getAllUsersInGroup(caminho);
	int num=users.size();
	return num;
}

public static String escolhido(String exclude){//, List<String> path){
	List user = usuariosDoGrupoExcludingOne(["colegiado"], exclude);
	if(ind==(numMembros(["colegiado"])-1)) 
		ind=0;
	String escolhido = user.get(ind);
	ind=ind+1;
	return escolhido;
}


public static String escolhido(){//, List<String> path){
	List user = usuariosDoGrupo(["colegiado"]);
	List user2 = usuariosDoGrupo(["coordenacao"]);
	user.addAll(user2);
	if(ind==(numMembros(["colegiado"])+numMembros(["coordenacao"]))) 
		ind=0;
	String escolhido = user.get(ind);
	ind=ind+1;
	return escolhido;
}

public static boolean numMembros(List<String> path, int conta){
	String caminho;
	Group g = AccessorUtil.getIdentityAPI().getGroupUsingPath(path);
	caminho=g.getUUID();
	List<User> users = AccessorUtil.getIdentityAPI().getAllUsersInGroup(caminho);
	int num=users.size();
	if(conta==num) return true;
	else return false;
}

public static  numMmebrosColegiado(List<String> path){//recebe uma list de string com o caminho do grupo
	String caminho;
	Group g = AccessorUtil.getIdentityAPI().getGroupUsingPath(path);
	caminho=g.getUUID();
	List<User> users = AccessorUtil.getIdentityAPI().getAllUsersInGroup(caminho);
	List<Object> userName;
	for(int i=0;i<users.size();i++){
	userName.add(users.get(i).username);
	}
	return userName;
	}
	


public static String nameOfLoggedUser() {
	final APIAccessor accessor = new StandardAPIAccessorImpl();
	String aluno = accessor.getManagementAPI().getLoggedUser();
   return aluno;
   }

public static String getCurrentDate(){
def cal = Calendar.instance;
String data = String.format("%02d-%02d-%d", 
    cal.get(Calendar.DATE), 
    cal.get(Calendar.MONTH), 
    cal.get(Calendar.YEAR));
/*
data= (''
    + cal.get(Calendar.DATE) + '-'
    + cal.get(Calendar.MONTH) + '-'
    + cal.get(Calendar.YEAR));
*/
return data;
}

public static String formataData(Date data){
			String finalData="";
		   SimpleDateFormat dateformatMMDDYYYY = new SimpleDateFormat("ddMMyyyy");
		   StringBuilder nowMMDDYYYY = new StringBuilder( dateformatMMDDYYYY.format(data) );
		 for(int i = 0; i < nowMMDDYYYY.length(); i++){
			   if(i == 1){
				  finalData = finalData + nowMMDDYYYY.charAt(i-1) + nowMMDDYYYY.charAt(i) + "/";
			}
			   if(i==3){
				   finalData = finalData + nowMMDDYYYY.charAt(i-1) + nowMMDDYYYY.charAt(i) + "/";
			   }
			   if(i==7){
				   finalData = finalData + nowMMDDYYYY.charAt(i-3) + nowMMDDYYYY.charAt(i-2) + nowMMDDYYYY.charAt(i-1)+ nowMMDDYYYY.charAt(i);
			   }
			   }
	return finalData;
		  // return nowMMDDYYYY;
	 	}
	

public static String concat (String a, String b){
	String acg;
	acg = a + "-" + b;
	return acg;
}


public static String concat2(String a, String b) {
	String email;
	email = a + b;
	return email;
}

public static int total(int conta) {
	conta=conta+1;
	return conta;
}


public static String getTime() {
	Calendar now = Calendar.getInstance();
	return Long.toString(now.getTimeInMillis());
}


public static String concat3(String time, String a, String b) {
	String nome;
	nome = a + "-" + time + b;
	return nome;
}


public static String mensagem(String tipo){
	String msg = "Para esse tipo de ACG voce precisar&aacute; anexar os seguintes documentos:";
	if (tipo == (tipoACG[0])) 
		return msg + "<ol><li>Certificado</li> <li>Se a carga hor&aacute;ria n&atilde;o estiver expl&iacute;cita no certificado, anexar a programa&ccedil;&atilde;o do evento</li> <li>No caso de organizador, anexar o relat&oacute;rio de atividades compat&iacute;vel com a carga hor&aacute;ria solicitada</li></ol>";
	if (tipo.equals(tipoACG[1])) 
		return msg + "<ol><li>Comprovante de registro do projeto na institui&ccedil;&atilde;o</li> <li>Relat&oacute;rio de atividades compat&iacute;vel com a carga hor&aacute;ria solicitada</li> <li>Parecer do tutor/orientador</li></ol>";
	if (tipo.equals(tipoACG[2])) 
		return msg + "<ol><li>Comprovante de registro do projeto na institui&ccedil;&atilde;o</li><li>Relat&oacute;rio de atividades compat&iacute;vel com a carga hor&aacute;ria solicitada</li> <li>Parecer do coordenador do projeto</li></ol>";
	if (tipo.equals(tipoACG[3])) 
		return msg + "<ol><li>Atestado</li> <li>Relat&oacute;rio de atividades compat&iacute;vel com a carga hor&aacute;ria solicitada</li> <li>Parecer do respons&aacute;vel pelo est&aacute;gio</li></ol>";
	if (tipo.equals(tipoACG[4])) 
		return msg + "<ol><li>Comprovante de registro do projeto na institui&ccedil;&atilde;o</li><li>Relat&oacute;rio de atividades compat&iacute;vel com a carga hor&aacute;ria solicitada</li> <li>Parecer do coordenador do projeto</li></ol>";
	if (tipo.equals(tipoACG[5])) 
		return msg + "<ol><li>C&oacute;pia da publica&ccedil;&atilde;o</li> <li>Identifica&ccedil;&atilde;o do ve&iacute;culo (evento, revista, etc.)</li></ol>";
	if (tipo.equals(tipoACG[6])) 
		return msg + "<ol><li>Portaria do colegiado e/ou atestado assinado pelo presidente do diret&oacute;rio</li></ol>";
	if (tipo.equals(tipoACG[7])) 
		return msg + "<ol><li>Comprovante de registro do projeto na institui&ccedil;&atilde;o</li> <li>Relat&oacute;rio de atividades compat&iacute;vel com a carga hor&aacute;ria solicitada</li> <li>Parecer do professor respons&aacute;vel pela disciplina</li></ol>";
	else 
		return msg + "<ol><li>Certificado com carga hor&aacute;ria</li</ol>";
}

public static ArrayList<String> listaSubtipos (String tipo) {
	List<String> resultado = new ArrayList();
	List<String> tipo1 = new ArrayList();
	tipo1.add("a. Ouvinte");
	tipo1.add("b. Expositor, apresentador,participacao.");
	tipo1.add("c. Organizacao");
	List<String> tipo2 = new ArrayList();
	tipo2.add("a. Atividade tecnica com projeto de ensino registrado no GAPE, exceto monitoria.");
	List<String> tipo3 = new ArrayList();
	tipo3.add("a. Participacao da Feira das Profissoes");
	tipo3.add("b. Projeto para comunidade extre-curso (com projeto de extensao registrado no GAPE)");
	List<String> tipo4 = new ArrayList();
	tipo4.add("a. Estagio com empresas conveniadas ao curso");
	tipo4.add("b. Estagio na propria instituicao (CPD, HUSM, Colegios Tecnicos, bolsista do NCC, entre outros)");
	List<String> tipo5 = new ArrayList();
	tipo5.add("a. Atividade em iniciacao cientifica com projeto de pesquisa registrados no GAP.");
	List<String> tipo6 = new ArrayList();
	tipo6.add("Resumo (uma pagina) - Publicacao Local");
	tipo6.add("Resumo (uma pagina) - Publicacao Regional");
	tipo6.add("Resumo (uma pagina) - Publicacao Nacional");
	tipo6.add("Resumo (uma pagina) - Publicacao Internacional");
	tipo6.add("Resumo Espandido (ate quatro paginas) -  Publicacao Local");
	tipo6.add("Resumo Espandido (ate quatro paginas) - Publicacao Regional");
	tipo6.add("Resumo Espandido (ate quatro paginas)- Publicacao Nacional");
	tipo6.add("Resumo Espandido (ate quatro paginas) - Publicacao Internacional");
	tipo6.add("Artigo Completo (acima de quatro paginas) - Publicacao Local");
	tipo6.add("Artigo Completo (acima de quatro paginas) - Publicacao Regional");
	tipo6.add("Artigo Completo (acima de quatro paginas) - Publicacao Nacional");
	tipo6.add("Artigo Completo (acima de quatro paginas) - Publicacap Internacional");
	List<String> tipo7 = new ArrayList();
	tipo7.add("a. Participacao de alunos regularmente matriculados no curso que tenham participacao em orgaos colegiados e diretorios academicos");
	List<String> tipo8 = new ArrayList();
	tipo8.add("a. Monitoria com projeto de ensino registrado no GAPE");
	List<String> tipo9 = new ArrayList();
	tipo9.add("a. Cursos de linguas estrangeiras");
	if (tipo.equals(tipoACG[0])) return tipo1;
	if (tipo.equals(tipoACG[1]))  return tipo2;
	if (tipo.equals(tipoACG[2])) return tipo3;
	if (tipo.equals(tipoACG[3])) return tipo4;
	if (tipo.equals(tipoACG[4])) return tipo5;
	if (tipo.equals(tipoACG[5])) return tipo6;
	if (tipo.equals(tipoACG[6])) return tipo7;
	if (tipo.equals(tipoACG[7])) return tipo8;
	else return tipo9;
}

public static List<Document> getAttachmentsOfProcessInstance(String processInstanceUUID) {
	QueryRuntimeAPI api = AccessorUtil.getQueryRuntimeAPI(AccessorUtil.QUERYLIST_HISTORY_KEY);
	DocumentSearchBuilder dsb = new DocumentSearchBuilder();
	dsb.criterion(DocumentIndex.PROCESS_INSTANCE_UUID).equalsTo(processInstanceUUID);
	DocumentResult res = api.searchDocuments(dsb, 0, 1000);
	return res.getDocuments();
}

public static String anexo(){
	// Accessor to get implementation of Bonita API
	StandardAPIAccessorImpl apiAccessorImpl = new StandardAPIAccessorImpl();
	
	// Get an instance of the query runtime API (allow to get information about running instance of processes)
	QueryRuntimeAPI queryRuntimeAPI = apiAccessorImpl.getQueryRuntimeAPI();
	
	// Create a search query to get the document attach to the process (latest version attached to the current process instance)
	DocumentSearchBuilder builder = new DocumentSearchBuilder();
	builder.latestVersion();
	builder.criterion(DocumentIndex.PROCESS_INSTANCE_UUID).equalsTo(getProcessInstanceUUID().getValue());
	
	// Run the search query
	DocumentResult documentResult = queryRuntimeAPI.searchDocuments(builder, 0, 1);
	
	// We should get only one document, check-it
	if(documentResult.getCount() == 1) {
	  // Get the document UUID
	  DocumentUUID documentUUID = documentResult.getDocuments().get(0).getUUID();
	
	  // Get the document content
	  byte[] documentRawContent = queryRuntimeAPI.getDocumentContent(documentUUID);
	  
	  // Create a temporary file to get the file content
	  File tempFile = File.createTempFile("tempUploadSharepoint", "tmp");
	
	  // Write the document content to a temporary file
	  FileOutputStream fos = new FileOutputStream(tempFile);
	  fos.write(documentRawContent);
	
	  // Return the path to the temporary file
	  return tempFile.getAbsolutePath();
	} else {
	return "oioioio";
	//throw new Exception("Incorrect number of attachments: " + documentResult.getCount());
	}
}

public static int difData(Date date1,Date date2){
def	cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
	cal.set(1981, 5, 16);  // 16 Jun 1981
	date1 = cal.time;
	cal.set(1973, 0, 18);  // 18 Jan 1973
	date2 = cal.time;
	int difference = Math.abs(date2.time - date1.time);
	int days = difference / (1000 * 60 * 60 * 24);
	return days;
	}

// urn:uuid:d9a46014-5e09-491e-8744-eef96046993e
public static String idFile(String link){
	String resultado = "";
	for(int i = 0; i < link.length(); i++){
	   if(i >= 9){
		  resultado = resultado + link.charAt(i);
	   }
	   }
	return resultado;
		 
	}

// File arquivo = new File("c:/luanmateus.txt");

public static void newFile(String fileName){
	File arquivo = new File("/home/jeh/Alfresco/" + fileName);
		  arquivo.createNewFile();
		 }

public static String createLink (String fileName, String link) {
	String finalLink = "";
	if (link != null) {
		finalLink = "<a href="+"\"http://bpm.inf.ufsm.br:9090/alfresco/d/d/workspace/SpacesStore/" + idFile(link) + "/" + fileName + "\"" + ">" + fileName + "</a>";
	}
	return finalLink;
}

public static String createLink2 (String fileName, String link) {
	String finalLink = "";
	if (link != null) {
		finalLink = "http://bpm.inf.ufsm.br:9090/alfresco/d/d/workspace/SpacesStore/" + idFile(link) + "/" + fileName;
	}
	return finalLink;
}

	}
