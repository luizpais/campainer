package br.com.gamoratech.campain.services;

import br.com.gamoratech.campain.model.Campain;
import br.com.gamoratech.campain.model.CampainDTO;
import br.com.gamoratech.campain.model.CampainMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class CampainService {

    // Handle log
    Logger LOG = Logger.getLogger(CampainService.class);

    // This service can be used to implement business logic related to campaigns.
    // For example, you can add methods to create, update, delete, and retrieve campaigns.
    // You can also add methods to handle specific business rules or validations.

    // Example method to create a new campaign
    @Transactional
    public void createCampain(String name, String description, String startDate, String endDate) {
        // Implementation for creating a new campaign
        Campain campain = new Campain(name, description, startDate, endDate);
        campain.persist(); // Assuming you have a method to persist the campaign
        LOG.infof("Campaign created: %s", campain.name);
    }

    // Example method to retrieve all campaigns
    public List<CampainDTO> getAllCampains() {
        // Implementation for retrieving all campaigns
        List<Campain> campains = Campain.listAll(); // Assuming you have a method to list all campaigns
        LOG.infof("Retrieved %d campaigns", campains.size());
        return CampainMapper.toDTOList(campains); // Convert to DTOs for response
    }

    // Add more methods as needed for your application logic
    // Example method to delete a campaign by ID
    @Transactional
    public void deleteCampain(Long id) {
        // Implementation for deleting a campaign by ID
        Campain campain = Campain.findById(id); // Assuming you have a method to find by ID
        if (campain != null) {
            campain.delete(); // Assuming you have a method to delete the campaign
            LOG.infof("Campaign deleted with ID: %d", id);
        } else {
            LOG.warnf("Attempted to delete non-existing campaign with ID: %d", id);
            throw new IllegalArgumentException("Campaign not found with ID: " + id);
        }
    }
    // Example method to update a campaign
    @Transactional
    public void updateCampain(CampainDTO campainDTO) {
        // Implementation for updating a campaign
        Campain campain = Campain.findById(campainDTO.getId()); // Assuming you have a method to find by ID
        if (campain != null) {
            campain.name = campainDTO.getName();
            campain.description = campainDTO.getDescription();
            campain.startDate = campainDTO.getStartDate();
            campain.endDate = campainDTO.getEndDate();
            campain.persist(); // Assuming you have a method to persist the updated campaign
            LOG.infof("Campaign updated: %s", campain.name);
        } else {
            LOG.warnf("Attempted to update non-existing campaign with ID: %d", campainDTO.getId());
            throw new IllegalArgumentException("Campaign not found with ID: " + campainDTO.getId());
        }

    }
    // Example method to find a campaign by ID
    public CampainDTO findCampainById(Long id) {
        // Implementation for finding a campaign by ID
        Campain campain = Campain.findById(id); // Assuming you have a method to find by ID
        if (campain == null) {
            LOG.warnf("Campaign not found with ID: %d", id);
            throw new IllegalArgumentException("Campaign not found with ID: " + id);
        }
        LOG.infof("Campaign found: %s", campain.name);
        return CampainMapper.toDTO(campain); // Convert to DTO for response
    }
}
