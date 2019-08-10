package com.iktpreobuka.schoolregister.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.iktpreobuka.schoolregister.entities.ParentEntity;
import com.iktpreobuka.schoolregister.entities.RegisterEntity;

@Service
public class EmailDaoImpl implements EmailDao {
	
	@Autowired
	public JavaMailSender emailSender;
	
	@Override
	public void sendTemplateMessage(ParentEntity p, RegisterEntity r) throws Exception {
		MimeMessage mail = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setTo(p.getEmail());
		helper.setSubject("NOVA OCENA ZA VASE DETE");
		String text = "<table border=\"2\" cellpadding=\"10\">\r\n" + 
				"<tbody>\r\n" + 
				"<tr>\r\n" + 
				"<td>Ime Prezime</td>\r\n" + 
				"<td>Datum</td>\r\n" + 
				"<td>Profesora</td>\r\n" + 
				"<td>Predmet</td>\r\n" + 
				"<td>Ocena</td>\r\n" + 
				"<td>Opis Ocene</td>\r\n" + 
				"<td>Polugodiste</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td>"+ r.getStudent().getName() +" "+ r.getStudent().getSurname() +"</td>\r\n" + 
				"<td>"+ r.getRegisterEntryDate() +"</td>\r\n" + 
				"<td>"+ r.getSchedule().getTeacher().getName() +" "+ r.getSchedule().getTeacher().getSurname() +"</td>\r\n" + 
				"<td>"+r.getSchedule().getSubject().getName() +"</td>\r\n" +
				"<td>"+r.getMarkValue().ordinal() +"</td>\r\n" +
				"<td>"+r.getMarkDefinition() +"</td>\r\n" +
				"<td>"+r.getSemester() +"</td>\r\n" + 
				"</tr>\r\n" + 
				"</tbody>\r\n" + 
				"</table>";
		helper.setText(text, true);
		emailSender.send(mail);

	}

}
