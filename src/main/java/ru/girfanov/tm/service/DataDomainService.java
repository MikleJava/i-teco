package ru.girfanov.tm.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.api.service.IDataDomainService;
import ru.girfanov.tm.dto.DataDomain;
import ru.girfanov.tm.entity.AbstractEntity;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@RequiredArgsConstructor
public final class DataDomainService implements IDataDomainService {

    @NotNull private static final String SERIALIZE_FILE = "src\\main\\resources\\data\\DataDomain.ser";
    @NotNull private static final String JAXB_XML_FILE = "src\\main\\resources\\data\\DataDomainJaxb.xml";
    @NotNull private static final String JAXB_JSON_FILE = "src\\main\\resources\\data\\DataDomainJaxb.json";
    @NotNull private static final String FASTER_XML_FILE = "src\\main\\resources\\data\\DataDomainFaster.xml";
    @NotNull private static final String FASTER_JSON_FILE = "src\\main\\resources\\data\\DataDomainFaster.json";

    @NonNull private IProjectRepository projectRepository;
    @NonNull private ITaskRepository taskRepository;
    @NonNull private IUserRepository userRepository;

    @Override
    public void saveDataBySerialization() {
        final File file = new File(SERIALIZE_FILE);
        try(final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            final Collection<AbstractEntity> list = new ArrayList<>();
            list.addAll(projectRepository.findAll());
            list.addAll(taskRepository.findAll());
            list.addAll(userRepository.findAll());
            for(AbstractEntity entity : list) {
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
                if(object instanceof Project) {
                    projectRepository.merge(((Project) object).getUserId(), (Project) object);
                }
                if(object instanceof Task) {
                    taskRepository.merge(((Task) object).getUserId(), (Task) object);
                }
                if(object instanceof User) {
                    userRepository.merge(((User) object).getUuid(), (User) object);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDataByJaxbInXml() {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(DataDomain.class);
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
            final JAXBContext jaxbContext = JAXBContext.newInstance(DataDomain.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            final DataDomain dataDomain = (DataDomain) unmarshaller.unmarshal(new File(JAXB_XML_FILE));
            updateDataDomain(dataDomain);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDataByJaxbInJson() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DataDomain.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
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
            final JAXBContext jaxbContext = JAXBContext.newInstance(DataDomain.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
            unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
            final DataDomain dataDomain = unmarshaller.unmarshal(new StreamSource(JAXB_JSON_FILE), DataDomain.class).getValue();
            updateDataDomain(dataDomain);
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
            final DataDomain dataDomain = xmlMapper.readValue(new File(FASTER_XML_FILE), DataDomain.class);
            updateDataDomain(dataDomain);
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
            final DataDomain dataDomain = objectMapper.readValue(new File(FASTER_JSON_FILE), DataDomain.class);
            updateDataDomain(dataDomain);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DataDomain getDataDomain() {
        final DataDomain dataDomain = new DataDomain();
        dataDomain.setProjects(projectRepository.findAll());
        dataDomain.setTasks(taskRepository.findAll());
        dataDomain.setUsers(userRepository.findAll());
        return dataDomain;
    }

    private void updateDataDomain(DataDomain dataDomain) {
        for(Project project : dataDomain.getProjects()) {
            projectRepository.merge(project.getUserId(), project);
        }
        for(Task task : dataDomain.getTasks()) {
            taskRepository.merge(task.getUserId(), task);
        }
        for(User user : dataDomain.getUsers()) {
            userRepository.merge(user.getUuid(), user);
        }
    }
}
