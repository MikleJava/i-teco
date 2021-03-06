package ru.girfanov.tm.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.NoArgsConstructor;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.girfanov.tm.api.service.IDataDomainService;
import ru.girfanov.tm.dto.DataDomainDto;
import ru.girfanov.tm.entity.AbstractEntity;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.repository.TaskRepository;
import ru.girfanov.tm.repository.UserRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

@Service
@NoArgsConstructor
public class DataDomainService implements IDataDomainService {

    @NotNull private static final String SERIALIZE_FILE = "./DataDomainDto.ser";
    @NotNull private static final String JAXB_XML_FILE = "./DataDomainJaxb.xml";
    @NotNull private static final String JAXB_JSON_FILE = "./DataDomainJaxb.json";
    @NotNull private static final String FASTER_XML_FILE = "./DataDomainFaster.xml";
    @NotNull private static final String FASTER_JSON_FILE = "./DataDomainFaster.json";

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveDataBySerialization() {
        final File file = new File(SERIALIZE_FILE);
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            final Collection<AbstractEntity> list = new ArrayList<>();
            list.addAll((Collection<? extends AbstractEntity>) projectRepository.findAll());
            list.addAll((Collection<? extends AbstractEntity>) taskRepository.findAll());
            list.addAll((Collection<? extends AbstractEntity>) userRepository.findAll());
            for (AbstractEntity entity : list) {
                objectOutputStream.writeObject(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDataBySerialization() {
        final File file = new File(SERIALIZE_FILE);
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object object;
            while ((object = objectInputStream.readObject()) != null) {
                if (object instanceof Project) {
                    projectRepository.merge(((Project) object).getName(), ((Project) object).getId());
                }
                if (object instanceof Task) {
                    taskRepository.merge(((Task) object).getName(), ((Task) object).getId());
                }
                if (object instanceof User) {
                    userRepository.merge(((User) object).getId(), ((User) object).getPassword());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDataByJaxbInXml() {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(DataDomainDto.class);
            final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(getDataDomain(), new File(JAXB_XML_FILE));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDataByJaxbInXml() {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(DataDomainDto.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            final DataDomainDto dataDomainDto = (DataDomainDto) unmarshaller.unmarshal(new File(JAXB_XML_FILE));
            updateDataDomain(dataDomainDto);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDataByJaxbInJson() {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(DataDomainDto.class);
            final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
            marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(getDataDomain(), new File(JAXB_JSON_FILE));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDataByJaxbInJson() {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(DataDomainDto.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
            unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
            final DataDomainDto dataDomainDto = unmarshaller.unmarshal(new StreamSource(JAXB_JSON_FILE), DataDomainDto.class).getValue();
            updateDataDomain(dataDomainDto);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDataByFasterInXml() {
        final XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            xmlMapper.writeValue(new File(FASTER_XML_FILE), getDataDomain());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDataByFasterInXml() {
        final XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            final DataDomainDto dataDomainDto = xmlMapper.readValue(new File(FASTER_XML_FILE), DataDomainDto.class);
            updateDataDomain(dataDomainDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDataByFasterInJson() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File(FASTER_JSON_FILE), getDataDomain());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDataByFasterInJson() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            final DataDomainDto dataDomainDto = objectMapper.readValue(new File(FASTER_JSON_FILE), DataDomainDto.class);
            updateDataDomain(dataDomainDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DataDomainDto getDataDomain() {
        final DataDomainDto dataDomainDto = new DataDomainDto();
        dataDomainDto.setProjects(projectRepository.findAll());
        dataDomainDto.setTasks(taskRepository.findAll());
        dataDomainDto.setUsers(userRepository.findAll());
        return dataDomainDto;
    }

    private void updateDataDomain(DataDomainDto dataDomainDto) {
        for(Project project : dataDomainDto.getProjects()) {
            projectRepository.merge(project.getName(), project.getId());
        }
        for(Task task : dataDomainDto.getTasks()) {
            taskRepository.merge(task.getName(), task.getId());
        }
        for(User user : dataDomainDto.getUsers()) {
            userRepository.merge(user.getId(), user.getPassword());
        }
    }
}
