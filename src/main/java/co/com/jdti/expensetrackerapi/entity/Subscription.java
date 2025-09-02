package co.com.jdti.expensetrackerapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "subscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", unique = true, nullable = false, updatable = false)
	private String id;

	@NotBlank
	@Column(nullable = false)
	private String title;

	private String description;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal amount;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BillingFrequency billingFrequency;

	@NotNull
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@NotNull
	@Column(name = "next_payment_date", nullable = false)
	private LocalDate nextPaymentDate;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PaymentStatus status = PaymentStatus.PENDING;

	@NotBlank
	@Column(nullable = false)
	private String vendor;

	private String notes;

	@Column(name = "is_active")
	private Boolean isActive = true;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		if (id == null) {
			id = UUID.randomUUID().toString();
		}
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public enum BillingFrequency {
		WEEKLY,
		MONTHLY,
		QUARTERLY,
		SEMI_ANNUALLY,
		ANNUALLY
	}
}
