package io.armory.plugin.example.spring.services

import com.netflix.spinnaker.kork.api.expressions.ExpressionFunctionProvider
import com.netflix.spinnaker.orca.capabilities.CapabilitiesService
import com.netflix.spinnaker.orca.capabilities.models.ExpressionCapabilityResult
import org.pf4j.PluginManager

class OverrideService(
        expressionFunctionProviders: MutableList<ExpressionFunctionProvider>,
        pluginManager: PluginManager?) : CapabilitiesService(expressionFunctionProviders,
        pluginManager) {

    override fun getExpressionCapabilities() : ExpressionCapabilityResult {
        val result = ExpressionCapabilityResult()
        result.functions.add(super.getExpressionCapabilities().functions.get(0))
        result.spelEvaluators.add(super.getExpressionCapabilities().spelEvaluators.get(0))
        return result
    }

}
